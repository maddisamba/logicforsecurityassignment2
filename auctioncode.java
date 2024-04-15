import java.util.*;

class Auction{
    int priceRaise=50; // {l}
    public void liveauction(){

        ////boolean sold=false;
        int bidStartingprice=getPriceFromOfflineBidders(); //{l} {liveauction:liveauction}
        System.out.print("start:");
        while(true){
            bidStartingprice=bidStartingprice+priceRaise;// {liveauction:liveauction}
           String offlineuser=matchofflinebidders(bidStartingprice);    // {liveauction:liveauction}
            String[] biddata = offlineuser.split(":", 2);
            if(biddata[0].equalsIgnoreCase("No user found for this price") ){
                //sold=true;
                System.out.println("going once, going twice, sold "); //{l}
                break;
            }
            else{
                System.out.println("auction house bids for "+biddata[0]+": "+biddata[1]+" kr");//{l}
                bidStartingprice= Integer.parseInt(biddata[1])+priceRaise; // {liveauction:liveauction}
            }

            String onlineuser=onlinebidders(bidStartingprice); // {liveauction:liveauction}

            if(onlineuser==null){
                //sold=true;
                System.out.println("going once, going twice, sold ");//{l}
                break;
            }
            else{
                System.out.println(onlineuser);//{l}
            }
        }

    }

    public String onlinebidders(int bidStartingprice){
        return "C bids for "+bidStartingprice+" kr";//ret= declassify(return_value, {onlinebidders:liveauction})
    }

    public String matchofflinebidders(int bidPrice){

        return getAnonymizedusers(bidPrice).toString(); //ret= declassify(return_value, {matchofflinebidders:liveauction})
    }

    public int getPriceFromOfflineBidders(){

        return (int)getAnonymizedusers(0); //ret= declassify(return_value, {getPriceFromOfflineBidders:liveauction})
    }

    public Object getAnonymizedusers(int price){//{l}
        HashMap<String,Integer> offlineBidders=new HashMap<>(); // {l} {anonimizedusers:anonimizedusers}
        offlineBidders.put("userA",500);
        offlineBidders.put("userB",700);
        if(price==0){
            List<Integer> prices=new ArrayList<>();
            prices.addAll(offlineBidders.values());// {l} {anonimizedusers:anonimizedusers}
            Collections.sort(prices);
            return prices.get(prices.size()-2);// declassify(return_value, {anonimizedusers:getPriceFromOfflineBidders})
        }
        else{


            for (String user:offlineBidders.keySet()){
                if(offlineBidders.get(user)>=price){
                    return  user+":"+price; // declassify(return_value, {anonimizedusers:matchofflinebidders})
                }
            }
            for (String user:offlineBidders.keySet()){
                if(offlineBidders.get(user)>=(price-priceRaise)){
                    return user+":"+(price-priceRaise); // declassify(return_value, {anonimizedusers:matchofflinebidders})
                }
            }
            return "No user found for this price:"+price; // declassify(return_value, {anonimizedusers:matchofflinebidders})
        }
    }

    public  static void main(String[] args){
        Auction auction=new Auction();
        auction.liveauction();
    }
}