import java.util.ArrayList;

public class Teacup {
    ArrayList<Integer> groupsUsed;
    int maxPrice;
    int groupNumber;

    public Teacup (ArrayList<Integer> list, int price, int number){
        groupsUsed = list;
        maxPrice = price;
        groupNumber = number;
    }
    public Teacup (int price, int number){
        groupsUsed = new ArrayList<Integer>();
        groupsUsed.add(number);
        maxPrice = price;
        groupNumber = number;
    }

    public Teacup(Teacup teacup, int price, int group, int gn){
        if(teacup != null) {
            maxPrice = teacup.maxPrice + price;

            groupsUsed= new ArrayList<>();
            if( teacup.groupsUsed != null) {
                groupsUsed.addAll(teacup.groupsUsed);
            }
            groupsUsed.add(group);
            groupNumber = gn;
        }else {
            groupsUsed = new ArrayList<Integer>();
            groupsUsed.add(gn);
            maxPrice = price;
            groupNumber = gn;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer group: groupsUsed) {
            if(group!=0) {
                stringBuilder.append(" ");
                stringBuilder.append(group);
            }
        }
        return "Best Sum for(" + groupNumber + " teacup) : $" + maxPrice + stringBuilder.toString();
    }

    public Teacup max(Teacup other){
        if(other == null) return this;
        if(this.maxPrice > other.maxPrice) return this;
        if(this.maxPrice < other.maxPrice) return other;

        //If they are the same price then return the one ith the smallest groupsUsed size.
        if(this.groupsUsed.size() < other.groupsUsed.size()) return this;
        else return other;
    }


}
