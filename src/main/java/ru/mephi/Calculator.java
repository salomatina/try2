package ru.mephi;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.correlation.Covariance;

import java.util.LinkedHashMap;

public class Calculator {

    public LinkedHashMap<String, Double[]> fillHashMap(double[][] cacheVariables) throws Exception {
        LinkedHashMap<String, Double[]> hashMap = new LinkedHashMap<>();
        // checking for weather data is recorded
        boolean isNotNull = false;
        for (double[] cacheVar : cacheVariables) {
            if (cacheVar != null) {
                isNotNull = true;
                break;
            }
        }
        if (!isNotNull) {
            throw new Exception("Data aren't found");
        }

        // calculating all the parameters
        Double[] temp = new Double[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = StatUtils.geometricMean(cacheVariables[i]);
        }
        hashMap.put("среднее геометрическое", temp);

        temp = new Double[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = StatUtils.mean(cacheVariables[i]);
        }
        hashMap.put("среднее арифметическое", temp);

        temp = new Double[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = Math.sqrt(StatUtils.variance(cacheVariables[i]));
        }
        hashMap.put("оценка стандартного отклонения", temp);

        temp = new Double[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = StatUtils.max(cacheVariables[i]) - StatUtils.min(cacheVariables[i]);
        }
        hashMap.put("размах", temp);

        temp = new Double[]{new Covariance().covariance(cacheVariables[0],cacheVariables[1]),
                new Covariance().covariance(cacheVariables[0], cacheVariables[2]),
                new Covariance().covariance(cacheVariables[2], cacheVariables[1])};
        hashMap.put("коэффициенты ковариации", temp);

        temp = new Double[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = (double) cacheVariables[i].length;
        }
        hashMap.put("количество элементов", temp);

        temp = new Double[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = Math.sqrt(StatUtils.variance(cacheVariables[i])) / Math.abs(StatUtils.mean(cacheVariables[i]));
        }
        hashMap.put("коэффициент вариации", temp);

        temp = new Double[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = StatUtils.mean(cacheVariables[i]) - (new TDistribution(cacheVariables[i].length - 1).inverseCumulativeProbability(0.95) * Math.sqrt(StatUtils.variance(cacheVariables[i]))) / Math.sqrt(cacheVariables[i].length);
        }
        hashMap.put("-доверительный интервал", temp);

        temp = new Double[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = StatUtils.mean(cacheVariables[i]) + (new TDistribution(cacheVariables[i].length - 1).inverseCumulativeProbability(0.95) * Math.sqrt(StatUtils.variance(cacheVariables[i]))) / Math.sqrt(cacheVariables[i].length);
        }
        hashMap.put("+доверительный интервал", temp);

        temp = new Double[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = StatUtils.variance(cacheVariables[i]);
        }
        hashMap.put("оценка дисперсии", temp);

        temp = new Double[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = StatUtils.max(cacheVariables[i]);
        }
        hashMap.put("максимумы", temp);

        temp = new Double[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = StatUtils.min(cacheVariables[i]);
        }
        hashMap.put("минимумы", temp);

        return hashMap;
    }

}
