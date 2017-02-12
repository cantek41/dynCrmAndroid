package veribis.veribiscrmdyn.Widgets.SelectableWidget;

/**
 * Created by Cantekin on 12.2.2017.
 * seçilebilinia alanlarda
 * dropdown gibi nesneler bu arayüzü implemente edecek
 */

public interface ISelectableWidget {
    /**
     * key ve value gerekli olan widgetler bunu implemente ederek kullanacak
     *
     * @param text
     * @param value
     */
    void setData(String text, String value);
}
