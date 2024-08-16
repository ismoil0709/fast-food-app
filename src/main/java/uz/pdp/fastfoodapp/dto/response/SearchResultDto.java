package uz.pdp.fastfoodapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.fastfoodapp.model.enums.SearchResultType;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class SearchResultDto {
    private UUID id;
    private SearchResultType type;
}
