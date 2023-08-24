@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String taskHolderName;
    private LocalDate taskDate;
    private String taskName;
    private String taskStatus;

    // Constructors, getters, setters
}