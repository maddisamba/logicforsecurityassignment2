class Auction{
    liveauction(){
        int priceRaise=50;
        boolean sold=false;
        int bidStartingprice=getPriceFromOfflineBidders();
        while(!sold){
            bidStartingprice=bidStartingprice+priceRaise;
           String offlineuser=matchofflinebidders(bidStartingprice);//for 750 we dont get any users
           System.out.println("auction house bids for B"+user+": 550 kr "+bidStartingprice);
            //System.out.println("auction house bids for B"+user+": 650 kr"+bidStartingprice+priceRaise);
            //System.out.println("auction house bids for B"+user+": 700 kr"+bidStartingprice+priceRaise);

           String onlineuser=onlinebidders();
            System.out.println("C bids 600 kr");
            //System.out.println("C bids 700 kr");
            //System.out.println("C bids 750 kr");
            if(offlineuser==null ){
                sold=true;
                System.out.println("going once, going twice, sold to C for 750 kr");
            }
            if(onlineuser==null){
                sold=true;
                //System.out.println("going once, going twice, sold to B for 750 kr");
            }
        }

    }

    onlinebidders(){
        return "C bids for 600 kr";
    }

    matchofflinebidders(int bidPrice){
        HashMap<String,Integer> User=getAnonymizedusers(bidPrice);
        return User.name();
    }

    getPriceFromOfflineBidders(){
        HashMap<String,Integer> offlineBidders=new HashMap<>();
        offlineBidders=getAnonymizedusers(0);

        int min=Integer.MAX_VALUE;
        int secondmin=Integer.MAX_VALUE;
        for (int price:offlineBidders.values()){
            if(price<min){
                secondmin=min;
                min=price;
            }
            else if(price<secondmin && price!=min){
                secondmin=price;
            }
        }
        return secondmin;
    }

    getAnonymizedusers(int price){
        HashMap<String,Integer> offlineBidders=new HashMap<>();
        offlineBidders.put("user1",500);
        offlineBidders.put("user2",700);
        HashMap<String,Integer> Users=new HashMap<>();
        for (String user:offlineBidders.keySet()){
            if(offlineBidders.get(user)>price){
                Users.put(user,offlineBidders.get(user));
            }
        }
        return Users;
    }
}