package tax.helper.domain.core.common.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonConverter {

    public static <T> T jsonToObject(final String jsonString, final Class<T> classOf) {
        Gson gson = new GsonBuilder().registerTypeAdapter(
            LocalDate.class,
            (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> LocalDate.parse(
                json.getAsJsonPrimitive().getAsString()
            )
        ).create();

        return gson.fromJson(jsonString, classOf);
    }
}
