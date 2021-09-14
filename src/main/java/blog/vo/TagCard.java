package blog.vo;

public class TagCard {

    private Long id;
    private String name;
    private int count; // 该tag下的博客数量

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TagCard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
