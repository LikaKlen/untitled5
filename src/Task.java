import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Task {
    private String taskName;
    private String description;
    private String dateCreated;

    public Task(String taskName, String description, String dateCreated) {
        this.taskName = taskName;
        this.description = description;
        this.dateCreated = dateCreated;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }


    static class DeadlineTask extends Task {
      private String deadlineDate;

      public DeadlineTask(String taskName, String description, String dateCreated, String deadlineDate) {
        super(taskName, description, dateCreated);
        this.deadlineDate = deadlineDate;
      }

      public String getDeadlineDate() {
        return deadlineDate;
      }

      public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
      }
    }

    static class RecurringTask extends Task {
    private String repeatInterval;
    private String startDate;

    public RecurringTask(String taskName, String description, String dateCreated, String repeatInterval, String startDate) {
        super(taskName, description, dateCreated);
        this.repeatInterval = repeatInterval;
        this.startDate = startDate;
    }

    public String getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(String repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    }

    static class TaskManager {
      private static int totalTasks = 0;
      private List<Task> tasks;

      public TaskManager() {
        this.tasks = new ArrayList<>();
      }

      public static int getTotalTasks() {
        return totalTasks;
      }

      public void addTask(Task task) {
        tasks.add(task);
        totalTasks++;
      }

      public void removeTask(Task task) {
        tasks.remove(task);
        totalTasks--;
      }

      public void viewTasks() {
          Collections.sort(tasks, Comparator.comparing(Task::getDateCreated));
          for (Task task : tasks) {
            System.out.println("Название: " + task.getTaskName());
            System.out.println("Описание: " + task.getDescription());
            System.out.println("Дата создания: " + task.getDateCreated());
            if (task instanceof DeadlineTask) {
                System.out.println("Дата дедлайна: " + ((DeadlineTask) task).getDeadlineDate());
            } else if (task instanceof RecurringTask) {
                System.out.println("Интервал повторения: " + ((RecurringTask) task).getRepeatInterval());
                System.out.println("Дата начала: " + ((RecurringTask) task).getStartDate());
            }
             System.out.println("--------------------");
          }
      }
    }


    public static void main(String[] args) {
        Task task1 = new Task("Задание 1", "Описание 1", "2022-01-01");
        DeadlineTask task2 = new DeadlineTask("Задание 2", "Описание 2", "2022-01-02", "2022-01-10");
        RecurringTask task3 = new RecurringTask("Задание 3", "Описание 3", "2022-01-03", "Ежедневные", "2022-01-03");

        TaskManager manager = new TaskManager();
        manager.addTask(task1);
        manager.addTask(task2);
        manager.addTask(task3);

        manager.viewTasks();

        System.out.println("Общее количество заданией: " + TaskManager.getTotalTasks());
    }
}
