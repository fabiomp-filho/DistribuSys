package fabio.distribusys.br.userms.pagination;

import java.util.List;

public interface CustomPage<T> {

    List<T> getContent();

    long getTotalElements();

    int getTotalPages();

    int getCurrentPage();
}
