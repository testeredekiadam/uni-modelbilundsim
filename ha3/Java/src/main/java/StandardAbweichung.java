public class StandardAbweichung {
    Long[] sa;
    int elemnum;
    double dn;
    long sum;


    public StandardAbweichung(int elemnum){
         this.sa = new Long[elemnum];
         this.elemnum = elemnum;
    }

    public void insertElement(int count, long element){

        this.sa[count] = element;
    }


    public long sum(){
        long sum = 0;
        this.sum = sum;
        Long[] newarr = this.sa.clone();
        for(long i : newarr){
            sum += i;
        }
        this.sum = sum;
        return this.sum;
    }

    public double durchschnitt(){

        this.dn = this.sum / this.elemnum;

        return this.dn;
    }

    public double variance(){
        double varianz = 0;

        Long[] temparr = this.sa.clone();
        for(long i : temparr){
            varianz += Math.pow((i-dn),2);
        }

        varianz /= (this.elemnum - 1);



        return varianz;
    }

    public double stdAbw(){
        return Math.sqrt(variance());
    }

}
