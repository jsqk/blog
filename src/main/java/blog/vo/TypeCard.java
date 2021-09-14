package blog.vo;

public class TypeCard {

    private Long id; // type的id标识符
    private String name; // type的名字
    private String introduce;
    private String cover; // type的封面
    private int counts; // 该类型的博客数量

    @Override
    public String toString() {
        return "TypeCard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", cover='" + cover + '\'' +
                ", counts=" + counts +
                '}';
    }

    public Long getId() {
        return id;
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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }
}
