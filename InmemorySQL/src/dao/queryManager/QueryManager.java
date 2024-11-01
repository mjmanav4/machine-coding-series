package dao.queryManager;

import dto.query.*;
import models.Row;

import java.util.List;

public interface QueryManager {

    Integer insert(InsertQueryDTO insertRecord);

    void delete(DeleteQueryDTO deleteQueryDTO);

    Row get(GetQueryDTO getQueryDTO);

    Row update(UpdateQueryDTO insertRecord);

    List<Row> printPaginated(PaginatedQueryDTO paginatedQueryDTO);

    List<Row> filterAndGet(FilterQueryDTO filterQueryDTO);


}
