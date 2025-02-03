package org.example.service;

import lombok.Getter;

import java.text.MessageFormat;

@Getter
public class StatisticService {

    private long integersCount = 0;
    private long floatsCount = 0;
    private long stringsCount = 0;
    private Long integersMin;
    private Long integersMax;
    private double integersSum = 0;
    private double integersAvg = 0;
    private Double floatsMin;
    private Double floatsMax;
    private double floatsSum = 0;
    private double floatsAvg = 0;
    private Integer stringsShortestSize;
    private Integer stringLongestSize;

    public String getStatistic(){
        return MessageFormat.format(
                "\n\t\tIntegers written: {0}\n\t\tFloats written: {1}\n\t\tStrings written: {2}",
                integersCount + "",
                floatsCount + "",
                stringsCount + ""
        );
    }

    public String getFullStatistic(){
        return MessageFormat.format(
                "\n\t\tIntegers written: {0} | Min: {1} | Max: {2} | Sum: {3} | Average: {4}" +
                        "\n\t\tFloats written: {5} | Min: {6} | Max: {7} | Sum: {8} | Average: {9}" +
                        "\n\t\tStrings written: {10} | The shortest size: {11} | The longest size: {12}",
                integersCount + "",
                integersMin + "",
                integersMax + "",
                integersSum + "",
                integersAvg + "",
                floatsCount + "",
                floatsMin + "",
                floatsMax + "",
                floatsSum + "",
                floatsAvg + "",
                stringsCount + "",
                stringsShortestSize + "",
                stringLongestSize + ""
        );
    }

    public void addInteger(Long value){
        integersCount++;
        if(integersMin != null)
            integersMin = Math.min(integersMin, value);
        else
            integersMin = value;
        if(integersMax != null)
            integersMax = Math.max(integersMax, value);
        else
            integersMax = value;
        integersSum += value;
    }

    public void addFloat(Double value){
        floatsCount++;
        if(floatsMin != null)
            floatsMin = Math.min(floatsMin, value);
        else
            floatsMin = value;
        if(floatsMax != null)
            floatsMax = Math.max(floatsMax, value);
        else
            floatsMax = value;
        floatsSum += value;
    }

    public void addString(String value){
        stringsCount++;
        if(stringsShortestSize != null)
            stringsShortestSize = Math.min(stringsShortestSize, value.length());
        else
            stringsShortestSize = value.length();
        if(stringLongestSize != null)
            stringLongestSize = Math.max(stringLongestSize, value.length());
        else
            stringLongestSize = value.length();
    }

    public void countAvg(){
        if(integersCount != 0)
            integersAvg = integersSum / integersCount;
        if(floatsCount != 0)
            floatsAvg = floatsSum / floatsCount;
    }

}
