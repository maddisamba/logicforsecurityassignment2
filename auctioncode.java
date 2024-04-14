import java.util.*;

class Auction{
    int priceRaise=50;
    public void liveauction(){

        boolean sold=false;
        int bidStartingprice=getPriceFromOfflineBidders();
        System.out.print("start:");
        while(!sold){
            bidStartingprice=bidStartingprice+priceRaise;
           String offlineuser=matchofflinebidders(bidStartingprice);
            String[] biddata = offlineuser.split(":", 2);
            if(biddata[0].equalsIgnoreCase("No user found for this price") ){
                sold=true;
                System.out.println("going once, going twice, sold ");
                break;
            }
            else{
                System.out.println("auction house bids for "+biddata[0]+": "+biddata[1]+" kr");
                bidStartingprice= Integer.parseInt(biddata[1])+priceRaise;
            }

            String onlineuser=onlinebidders(bidStartingprice);

            if(onlineuser==null){
                sold=true;
                System.out.println("going once, going twice, sold ");
                break;
            }
            else{
                System.out.println(onlineuser);
            }
        }

    }

    public String onlinebidders(int bidStartingprice){
        return "C bids for "+bidStartingprice+" kr";
    }

    public String matchofflinebidders(int bidPrice){
        return getAnonymizedusers(bidPrice).toString();
    }

    public int getPriceFromOfflineBidders(){
        return (int)getAnonymizedusers(0);
    }

    public Object getAnonymizedusers(int price){
        HashMap<String,Integer> offlineBidders=new HashMap<>();
        offlineBidders.put("userA",500);
        offlineBidders.put("userB",700);
        if(price==0){
            List<Integer> prices=new ArrayList<>();
            prices.addAll(offlineBidders.values());
            Collections.sort(prices);
            return prices.get(prices.size()-2);
        }
        else{


            for (String user:offlineBidders.keySet()){
                if(offlineBidders.get(user)>=price){
                    return  user+":"+price;
                }
            }
            for (String user:offlineBidders.keySet()){
                if(offlineBidders.get(user)>=(price-priceRaise)){
                    return user+":"+(price-priceRaise);
                }
            }
            return "No user found for this price:"+price;
            //return offlineBidders.entrySet().stream().filter(entry->entry.getValue()>=price).map(Map.Entry::getKey).findFirst().get();
        }
    }

    public  static void main(String[] args){
        Auction auction=new Auction();
        auction.liveauction();
    }
}