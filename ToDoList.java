public class ToDoList {

    static void findWaitingTime(int processes[], int n, int bt[], int wt[], int at[])
    {
        int service_time[] = new int[n];
        service_time[0] = at[0];
        wt[0] = 0;
    
        // расчет времени ожидания
        for (int i = 1; i < n ; i++)
        {
            //потерянное время
            int wasted=0;
            // время разрыва предыдущих процессов
            service_time[i] = service_time[i-1] + bt[i-1];
    
            // Нахождение времени ожидания для текущего процесса =
            // sum - at[i]
            wt[i] = service_time[i] - at[i];
    
            if (wt[i] < 0) {
                wasted = Math.abs(wt[i]);
                wt[i] = 0;
            }
            //Добавить потерянное время
            service_time[i] = service_time[i] + wasted;
        }
    }
    
    // Функция для расчета времени выполнения
    static void findTurnAroundTime(int processes[], int n, int bt[],
                                        int wt[], int tat[])
    {
        // Расчет времени выполнения путем добавления bt[i] + wt[i]
        for (int i = 0; i < n ; i++)
            tat[i] = bt[i] + wt[i];
    }
    
    // Функция для расчета среднего значения количества раз ожидания и выполнения

    static void findavgTime(int processes[], int n, int bt[], int at[])
    {
        int wt[] = new int[n], tat[] = new int[n];
    
        // Функция для нахождения времени ожидания всех процессов
        findWaitingTime(processes, n, bt, wt, at);
    
        // Функция для нахождения времени выполнения для всех процессов
        findTurnAroundTime(processes, n, bt, wt, tat);
    
        System.out.print("Процессы " + " Время разрыва процессов " + " Время поступления "
            + " Время ожидания " + " Время выполнения "
            + " Время завершения \n");
        int total_wt = 0, total_tat = 0;
        for (int i = 0 ; i < n ; i++)
        {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            int compl_time = tat[i] + at[i];
            System.out.println(i+1 + "\t\t" + bt[i] + "\t\t"
                + at[i] + "\t\t" + wt[i] + "\t\t "
                + tat[i] + "\t\t " + compl_time);
        }
    
        System.out.print("Среднее время ожидания = "
            + (float)total_wt / (float)n);
        System.out.print("\nСреднее время выполнения = "
            + (float)total_tat / (float)n);
            System.out.println();
    }
    
        public static void main(String args[]) {
            // Идентификаторы процессов
        int processes[] = {1, 2, 3};
        int n = processes.length;
    
        // Время разрыва всех процессов
        int burst_time[] = {5, 9, 6};
    
        // Время поступления всех процессов
        int arrival_time[] = {0, 3, 6};
    
        findavgTime(processes, n, burst_time, arrival_time);
    
        }
    }
