package notebook.dao;
import java.util.List;
/**
 * Data Access Object (DAO) ñëîé, ñ ìåòîäàìè äëÿ ðàáîòû ñ ÁÄ
 * @param <T> òèï îáúåêòà äëÿ ÷òåíèÿ/çàïèñè.
 * Data Access Object (DAO) слой с методом бд
 * @param <T> ��� ������� ��� ������/������.
 */
public interface Operation<T> {
    List<T> readAll();
    void saveAll(List<T> data);
}
