package generics;

public interface B extends A<String> {
    @Override
    public void m(String str);
}
