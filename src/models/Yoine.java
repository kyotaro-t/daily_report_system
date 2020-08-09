    package models;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "yoines")
@NamedQueries({
    @NamedQuery(
        name = "getYoineCount",
        query = "SELECT COUNT(y) FROM Yoine AS y WHERE y.report = :report"
        ),

    @NamedQuery(
            name = "getYoine",
            query = "SELECT COUNT(y) FROM Yoine AS y WHERE y.report = :report AND y.employee = :employee"
            ),

    @NamedQuery(
            name = "destroyYoine",
            query = "SELECT y FROM Yoine AS y WHERE y.report = :report AND y.employee = :employee"
            )
})
@Entity
public class Yoine {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    @JoinColumn(name = "yoineCount", nullable = false)
    private Integer yoines_count;

    @JoinColumn(name = "yoine", nullable = false)
    private Integer yoine;

    public Integer getYoines_count() {
        return yoines_count;
    }

    public void setYoines_count(Integer yoines_count) {
        this.yoines_count = yoines_count;
    }

    public Integer getYoine() {
        return yoine;
    }

    public void setYoine(Integer yoine) {
        this.yoine = yoine;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}


