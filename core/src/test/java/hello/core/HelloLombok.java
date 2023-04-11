package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
    Lombok 설정법
    - build.gradle에서 lombok 설정 및 lib추가
    - gradle reLoad 후 추가됐는지 확인
    - plugIn에서도 lombok 설치됐는지 확인. 없으면 설치
    - settings에서 annotation Processor 사용 체크
*/

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("진효주");

        //String name = helloLombok.getName();
        //System.out.println("name = " + name);
        System.out.println("helloLombok = " + helloLombok);
    }
}
