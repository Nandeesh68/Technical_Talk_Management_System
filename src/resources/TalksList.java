package resources;

public class TalksList {


    TalkInfo t1;
    TalkInfo t2;
    TalkInfo t3;

    public TalksList(){

        t1 = new TalkInfo(1,"2019-20","01/12/2021  11:00 AM","1 hours","John","Machine Learning","Key points related to Machine Learning");
        t2 = new TalkInfo(2,"2020-21","03/10/2020  03:00 PM","2 hours","James","IOT","Key points related to IOT");
        t3 = new TalkInfo(3,"2021-22","04/08/2019  04:00 PM","3 hours","Raja","Blockchain","Key points related to Blockchain");


        Resources.talksDetails.add(t1);
        Resources.talksDetails.add(t2);
        Resources.talksDetails.add(t3);
    }
}
