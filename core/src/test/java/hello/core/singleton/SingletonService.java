package hello.core.singleton;

public class SingletonService {

    // static 영역에 객체 instance를 미리 하나 생성해 올려둠 (자바 뜰 때 바로 뜰 수 있게)
    private static final SingletonService instance = new SingletonService();

    // 해당 객체가 필요하면 오직 getInstance() 메서드를 통해서만 조회 가능케
    // 호출 시 항상 같은 메서드 반환
    public static SingletonService getInstance(){
        return instance;
    }

    // 딱 1개의 객체 인스턴스만 존재해야하므로 생성자를 private로 막아 외부사용 막음
    // 외부사용 => new 키워드로 객체 인스턴스 생성되는 것
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
