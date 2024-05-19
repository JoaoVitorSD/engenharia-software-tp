package tp.gerenciamento.infraestructure.adapter.mapper;

import tp.gerenciamento.domain.output.Pagina;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.function.Function;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public  class PageJpaMapper {


    public static <T1,T2> Pagina<T1> pageJpaToDomain(Page<T2> page, Function<T2,T1> mapper){
        Pagina<T1> pagina = new Pagina<>();
        pagina.setContent(page.getContent().stream().map(mapper).toList());
        pagina.setPage(page.getNumber());
        pagina.setSize(page.getSize());
        pagina.setNumberOfElements(page.getNumberOfElements());
        pagina.setTotalElements(page.getTotalElements());
        pagina.setTotalPages(page.getTotalPages());
        return pagina;
    }
}
