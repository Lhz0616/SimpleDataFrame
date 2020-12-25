package math;

public class Scalers {

    Statistics s = new Statistics();

    public float [] StandardScale(){
        float [] mean = s.mean();
        float [] sd = s.sd();

        for(int i = 0; i<s.calculate.size(); i++){
            float [] temp = s.calculate.get(i);
            for(int j = 0; j<temp.length; j++){

            }
        }
        return new float[]{};
    }

    public float[] minMaxScaling(){

        return new float[]{};
    }
}
