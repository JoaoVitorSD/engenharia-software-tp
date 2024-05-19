package ai.attus.gerenciamento.domain.output;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Pagina <T>{
    List<T> content;
    long totalElements;
    int totalPages;
    int size;
    int numberOfElements;
    int page;


    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public <R> Pagina<R> map(Function<T, R> mapper){
        Pagina<R> pagina = new Pagina<>();
        pagina.setContent(content.stream().map(mapper).collect(Collectors.toList()));
        pagina.setPage(page);
        pagina.setSize(size);
        pagina.setTotalElements(totalElements);
        pagina.setTotalPages(totalPages);
        pagina.setNumberOfElements(numberOfElements);
        return pagina;
    }
}
