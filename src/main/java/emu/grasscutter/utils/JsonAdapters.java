package emu.grasscutter.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.data.common.DynamicFloat;
import emu.grasscutter.game.quest.enums.QuestCond;
import emu.grasscutter.game.quest.enums.QuestContent;
import emu.grasscutter.game.quest.enums.QuestExec;
import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import lombok.val;

public class JsonAdapters {
    static class DynamicFloatAdapter extends TypeAdapter<DynamicFloat> {
        @Override
        public DynamicFloat read(JsonReader reader) throws IOException {
            switch (reader.peek()) {
                case STRING:
                    return new DynamicFloat(reader.nextString());
                case NUMBER:
                    return new DynamicFloat((float) reader.nextDouble());
                case BOOLEAN:
                    return new DynamicFloat(reader.nextBoolean());
                case BEGIN_ARRAY:
                    reader.beginArray();
                    val opStack = new ArrayList<DynamicFloat.StackOp>();
                    while (reader.hasNext()) {
                        opStack.add(switch (reader.peek()) {
                            case STRING -> new DynamicFloat.StackOp(reader.nextString());
                            case NUMBER -> new DynamicFloat.StackOp((float) reader.nextDouble());
                            case BOOLEAN -> new DynamicFloat.StackOp(reader.nextBoolean());
                            default -> throw new IOException("Invalid DynamicFloat definition - " + reader.peek().name());
                        });
                    }
                    reader.endArray();
                    return new DynamicFloat(opStack);
                default:
                    throw new IOException("Invalid DynamicFloat definition - " + reader.peek().name());
            }
        }

        @Override
        public void write(JsonWriter writer, DynamicFloat f) {};
    }

    static class IntListAdapter extends TypeAdapter<IntList> {
        @Override
        public IntList read(JsonReader reader) throws IOException {
            switch (reader.peek()) {
                case BEGIN_ARRAY:
                    reader.beginArray();
                    val i = new IntArrayList();
                    while (reader.hasNext())
                        i.add(reader.nextInt());
                    reader.endArray();
                    i.trim();  // We might have a ton of these from resources and almost all of them immutable, don't overprovision!
                    return i;
                default:
                    throw new IOException("Invalid IntList definition - " + reader.peek().name());
            }
        }

        @Override
        public void write(JsonWriter writer, IntList l) throws IOException {
            writer.beginArray();
            for (val i : l)  // .forEach() doesn't appreciate exceptions
                writer.value(i);
            writer.endArray();
        };
    }

    static class PositionAdapter extends TypeAdapter<Position> {
        @Override
        public Position read(JsonReader reader) throws IOException {
            switch (reader.peek()) {
                case BEGIN_ARRAY:  // "pos": [x,y,z]
                    reader.beginArray();
                    val array = new FloatArrayList(3);
                    while (reader.hasNext())
                        array.add((float)reader.nextDouble());
                    reader.endArray();
                    return new Position(array);
                case BEGIN_OBJECT:  // "pos": {"x": x, "y": y, "z": z}
                    float x = 0f;
                    float y = 0f;
                    float z = 0f;
                    reader.beginObject();
                    for (var next = reader.peek(); next != JsonToken.END_OBJECT; next = reader.peek()) {
                        val name = reader.nextName();
                        switch (name) {
                            case "x", "X", "_x" -> x = (float) reader.nextDouble();
                            case "y", "Y", "_y" -> y = (float) reader.nextDouble();
                            case "z", "Z", "_z" -> z = (float) reader.nextDouble();
                            default -> throw new IOException("Invalid field in Position definition - " + name);
                        }
                    }
                    reader.endObject();
                    return new Position(x, y, z);
                default:
                    throw new IOException("Invalid Position definition - " + reader.peek().name());
            }
        }

        @Override
        public void write(JsonWriter writer, Position i) throws IOException {
            writer.beginArray();
            writer.value(i.getX());
            writer.value(i.getY());
            writer.value(i.getZ());
            writer.endArray();
        };
    }

    static class GridPositionAdapter extends TypeAdapter<GridPosition> {
        @Override
        public GridPosition read(JsonReader reader) throws IOException {
            switch (reader.peek()) {
                case BEGIN_ARRAY:  // "pos": [x,z,width]
                    reader.beginArray();
                    val array = new IntArrayList(3);
                    while (reader.hasNext())
                        array.add(reader.nextInt());
                    if(array.size() != 3) throw new IOException("invalid size on GridPosition definition - ");
                    reader.endArray();
                    return new GridPosition(array);
                case STRING:  // "(x,z,width)"
                    return new GridPosition(reader.nextString());
                default:
                    throw new IOException("Invalid Position definition - " + reader.peek().name());
            }
        }

        @Override
        public void write(JsonWriter writer, GridPosition i) throws IOException {
            writer.beginArray();
            writer.value(i.getX());
            writer.value(i.getZ());
            writer.value(i.getWidth());
            writer.endArray();
        };
    }

    static class QuestAcceptConditionAdapter extends QuestEnumAdapter<QuestCond> {
        QuestAcceptConditionAdapter(){
            super(QuestCond.class, QuestCond.QUEST_COND_UNKNOWN, null);
        }
    }
    static class QuestContentAdapter extends QuestEnumAdapter<QuestContent> {
        QuestContentAdapter(){
            super(QuestContent.class, QuestContent.QUEST_CONTENT_UNKNOWN, null);
        }
    }
    static class QuestExecAdapter extends QuestEnumAdapter<QuestExec> {
        QuestExecAdapter(){
            super(QuestExec.class, QuestExec.QUEST_EXEC_UNKNOWN, null);
        }
    }

    static class QuestEnumAdapter<T extends Enum<T>> extends TypeAdapter<T> {
        Class<T> enumClass;
        T unknownValue;
        T missingValue;

        QuestEnumAdapter(Class<T> enumClass, T unknownValue, T missingValue){
            this.enumClass = enumClass;
            this.unknownValue = unknownValue;
            this.missingValue = missingValue;
        }

        @Override
        public T read(JsonReader reader) throws IOException {
            val key = reader.nextString();
            if(key == null){
                return missingValue;
            }
            try {
                return Enum.valueOf(enumClass, key);
            } catch (Exception ex){
                Grasscutter.getLogger().info("missing quest enum value {}", key);
            }
            return unknownValue;
        }

        @Override
        public void write(JsonWriter writer, T i) {};
    }

    static class EnumTypeAdapterFactory implements TypeAdapterFactory {
        @SuppressWarnings("unchecked")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            Class<T> enumClass = (Class<T>) type.getRawType();
            if (!enumClass.isEnum()) return null;

            // Make mappings of (string) names to enum constants
            val map = new HashMap<String, T>();
            val enumConstants = enumClass.getEnumConstants();
            for (val constant : enumConstants)
                map.put(constant.toString(), constant);

            // If the enum also has a numeric value, map those to the constants too
            // System.out.println("Looking for enum value field");
            for (Field f : enumClass.getDeclaredFields()) {
                if (switch (f.getName()) {case "value", "id" -> true; default -> false;}) {
                    // System.out.println("Enum value field found - " + f.getName());
                    boolean acc = f.isAccessible();
                    f.setAccessible(true);
                    try {
                        for (val constant : enumConstants)
                            map.put(String.valueOf(f.getInt(constant)), constant);
                    } catch (IllegalAccessException e) {
                        // System.out.println("Failed to access enum id field.");
                    }
                    f.setAccessible(acc);
                    break;
                }
            }

            return new TypeAdapter<T>() {
                public T read(JsonReader reader) throws IOException {
                    switch (reader.peek()) {
                        case STRING:
                            return map.get(reader.nextString());
                        case NUMBER:
                            return map.get(String.valueOf(reader.nextInt()));
                        default:
                            throw new IOException("Invalid Enum definition - " + reader.peek().name());
                    }
                }
                public void write(JsonWriter writer, T value) throws IOException {
                    writer.value(value.toString());
                }
            };
        }
    }
}
