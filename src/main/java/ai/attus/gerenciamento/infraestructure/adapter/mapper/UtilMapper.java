package ai.attus.gerenciamento.infraestructure.adapter.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public  class UtilMapper {
    public static UUID fromString(String id) {
        return id != null ? UUID.fromString(id) : null;
    }

}
