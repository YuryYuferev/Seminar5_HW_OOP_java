package notebook.mapper;

/**
 * Êîíâåðòåð ìîäåëåé èç îäíîãî òèïà â äðóãîé.
 * @param <E> òèï, õðàíèìûé â ÁÄ
 * @param <T> òèï, ïîäõîäÿùèé äëÿ êîíâåðòàöèè E
 */
public interface Mapper<E, T> {
    T toInput(E e);
    E toOutput(T t);
}
