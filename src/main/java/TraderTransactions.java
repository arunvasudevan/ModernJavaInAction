import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class TraderTransactions {

    public static void main(String[] args){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );
        TraderTransactions traderTransactions=new TraderTransactions();

        traderTransactions.findAllTransactionsbyYear(transactions, 2011);

        traderTransactions.uniqueCityByTraders(transactions, "Cambridge");

    }

    public void findAllTransactionsbyYear(List<Transaction> transactions, int year){
        System.out.println("All Transactions by year:"+year);
        List<Transaction> transactionList = transactions.stream()
            .filter(t -> t.getYear() == year)
            .sorted(Comparator.comparing(Transaction::getValue))
            .collect(toList());

        for (Transaction transaction: transactionList
             ) {
            System.out.println(transaction);
        }
    }


    public void uniqueCityByTraders(List<Transaction> transactions, String city){
        System.out.println("Unique City by trader city:"+city);
        List<Transaction> tradersUniqueCityList = transactions.stream()
            .filter(t -> t.getTrader().getCity().equalsIgnoreCase(city))
            .distinct()
            .collect(toList());

        for (Transaction uniqueCity:tradersUniqueCityList
             ) {
            System.out.println(uniqueCity);
        }

    }
}
