package gui.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;

/**
 * @author Mark
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class SimpleListModel extends AbstractListModel implements MutableComboBoxModel {

   private List items;
   private Object selectedItem;

   public SimpleListModel() {
      items = new ArrayList();
   }

   public SimpleListModel(Collection items) {
      this.items = new ArrayList(items);
   }

   public int getSize() {
      return items.size();
   }

   public Object getElementAt(int index) {
      return items.get(index);
   }

   public void addElement(Object obj) {
      fireIntervalAdded(this, items.size() - 1, items.size() - 1);
      items.add(obj);
   }

   public void removeElement(Object obj) {
      int index = items.indexOf(obj);
      if (index != -1) {
         removeElementAt(index);
      }
   }

   public void insertElementAt(Object obj, int index) {
      items.add(index, obj);
      fireIntervalAdded(this, index, index);
   }

   public void removeElementAt(int index) {
      items.remove(index);
      fireIntervalRemoved(this, index, index);
   }

   public void setSelectedItem(Object anItem) {
      this.selectedItem = anItem;
      fireContentsChanged(this, -1, -1);
   }

   public Object getSelectedItem() {
      return selectedItem;
   }

   public void updateItems(Collection newItems) {
      int oldSize = this.items.size();
      this.items = new ArrayList(newItems);
      fireContentsChanged(this, -1, oldSize - 1);
   }

   public void updateItems(Object item) {
      int oldSize = this.items.size();
      this.items.clear();
      this.items.add(item);
      fireContentsChanged(this, -1, oldSize - 1);
   }

   public boolean contains(Object obj) {
      return this.items.contains(obj);
   }

   public int indexOf(Object obj) {
      return this.items.indexOf(obj);
   }

   public void clear() {
      this.items.clear();
      fireContentsChanged(this, -1, -1);
   }
}
