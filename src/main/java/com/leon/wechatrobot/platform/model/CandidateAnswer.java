package com.leon.wechatrobot.platform.model;

/**
 * Created on 18/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class CandidateAnswer implements Comparable<CandidateAnswer>{
    //答案上下文
    private String answer;
    private double score;

    public CandidateAnswer() {

    }

    public CandidateAnswer(String answer, double score) {
        this.answer = answer;
        this.score = score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public void addScore(double score) {
        this.score += score;
    }


    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public int hashCode() {
        return this.answer.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CandidateAnswer)) {
            return false;
        }
        CandidateAnswer a = (CandidateAnswer) obj;
        return this.answer.equals(a.answer);
    }

    @Override
    public int compareTo(CandidateAnswer o) {
        if (o != null && o instanceof CandidateAnswer) {
            CandidateAnswer a = (CandidateAnswer) o;
            if (this.score < a.score) {
                return -1;
            }
            if (this.score > a.score) {
                return 1;
            }
            if (this.score == a.score) {
                return 0;
            }
        }
        throw new RuntimeException("无法比较大小");
    }
}
