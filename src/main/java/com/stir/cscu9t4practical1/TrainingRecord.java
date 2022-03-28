// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;




import java.util.*;


public class TrainingRecord {
    private List<Entry> tr;
    
    public TrainingRecord() {
        tr = new ArrayList<Entry>();
    } //constructor
    
    // add a record to the list
   public String addEntry(Entry e){
        String msg = "Cannot add duplicates\n";
        if(!hasDuplicate(e))
        {
            msg = "Record added\n";
            tr.add(e);
        }
        return msg;
   } // addClass
   
   // look up the entry of a given day and month
   public String lookupEntry (int d, int m, int y) {
       ListIterator<Entry> iter = tr.listIterator();
       String result = "No entries found";
       while (iter.hasNext()) {
          Entry current = iter.next();
          if (current.getDay()==d && current.getMonth()==m && current.getYear()==y) 
             result = current.getEntry();
            }
       return result;
   } // lookupEntry

    // look up the entry of a given day and month
    public String lookupEntries (int d, int m, int y) {
        ListIterator<Entry> iter = tr.listIterator();
        String result = "";
        while (iter.hasNext()) {
            Entry current = iter.next();
            if (current.getDay()==d && current.getMonth()==m && current.getYear()==y)
                result += current.getEntry() + ", ";
        }
        if (result.length()==0){
            result = "No entries found";
        }
        return result;
    } // lookupEntry

    public String removeEntry(String n, int d, int m, int y)
    {
        String msg = "Nothing was removed";
        ListIterator<Entry> iter = tr.listIterator();
        while (iter.hasNext()) {
            Entry current = iter.next();
            if (current.getDay()==d && current.getMonth()==m && current.getYear()==y && current.getName().equals(n)) {
                iter.remove();
                msg = "Entry removed";
            }
        }
        return msg;
    }

    public boolean hasDuplicate(Entry e)
    {
        boolean hasDupl = false;
        ListIterator<Entry> iter = tr.listIterator();
        while (iter.hasNext()) {
            Entry current = iter.next();
            if (current.getDay()==e.getDay() && current.getMonth()==e.getMonth() && current.getYear()==e.getYear() && current.getName().equals(e.getName())) {
                hasDupl = true;
            }
        }
        return hasDupl;
    }
   
   // Count the number of entries
   public int getNumberOfEntries(){
       return tr.size();
   }
   // Clear all entries
   public void clearAllEntries(){
       tr.clear();
   }
   
} // TrainingRecord