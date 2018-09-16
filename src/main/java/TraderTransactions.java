import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;


/**
 *
 1. Find all transactions in the year 2011 and sort them by value (small to high).
 2. What are all the unique cities where the traders work?
 3. Find all traders from Cambridge and sort them by name.
 4. Return a string of all traders’ names sorted alphabetically.
 5. Are any traders based in Milan?
 6. Print all transactions’ values from the traders living in Cambridge.
 7. What’s the highest value of all the transactions?
 8. Find the transaction with the smallest value.
 */
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

        traderTransactions.uniqueCityByTraders(transactions);

        traderTransactions.findAllTradersInaCity(transactions, "Cambridge");

        traderTransactions.allTradersSortedByName(transactions);

        traderTransactions.anyTradersInACity(transactions, "Milan");

        traderTransactions.printTransactionValuesByTraderCity(transactions, "Cambridge");

        traderTransactions.maxTransactionValue(transactions);

        traderTransactions.minTransactionValue(transactions);
    }

    public void findAllTransactionsbyYear(List<Transaction> transactions, int year){
        System.out.println("1. All Transactions in year:"+year);
        transactions.stream()
            .filter(transaction -> transaction.getYear() == year)
            .sorted(comparing(Transaction::getValue))
            .forEach(System.out::println);
    }


    public void uniqueCityByTraders(List<Transaction> transactions){
        System.out.println();
        String uniqueCities = transactions.stream()
            .map(transaction -> transaction.getTrader().getCity())
            .distinct()
            .collect(joining(","));

        System.out.println("2. Unique Cities by traders:"+uniqueCities);
    }

    public void findAllTradersInaCity(List<Transaction> transactions, String city){
        System.out.println();
        System.out.println("3. Filter Trader by "+city+"(sorted):");
        transactions.stream()
            .map(Transaction::getTrader)
            .filter(trader -> trader.getCity().equalsIgnoreCase(city))
            .distinct()
            .sorted(comparing(Trader::getName))
            .forEach(System.out::println);
    }

    public void allTradersSortedByName(List<Transaction> transactions) {
        System.out.println();
        String str = transactions.stream()
            .map(transaction -> transaction.getTrader().getName())
            .distinct()
            .sorted()
            .collect(joining(","));

        System.out.println("4. All Traders Sorted by Name:" + str);
    }

    public void anyTradersInACity(List<Transaction> transactions, String city){
        System.out.println();
        boolean isTraderPresentInCity= transactions.stream()
            .anyMatch(transaction -> transaction.getTrader().getCity().equalsIgnoreCase(city));

        System.out.println("5. Is Any Trader present in "+city+": "+isTraderPresentInCity);
    }

    public void printTransactionValuesByTraderCity(List<Transaction> transactions, String city){
        System.out.println();
        System.out.print("6. Print all transactions’ values from the traders living in Cambridge:");
        transactions.stream()
            .filter(transaction-> transaction.getTrader().getCity().equalsIgnoreCase(city))
            .map(Transaction::getValue)
            .forEach(System.out::print);
    }

    public void maxTransactionValue(List<Transaction> transactions){
        System.out.println();
        System.out.println();
        System.out.print("7. Highest value of all the transactions:");

        Optional<Transaction> maxTransaction=transactions.stream()
            .max(comparing(Transaction::getValue));

        System.out.print(maxTransaction.get().getValue());
        System.out.println();
    }

    public void minTransactionValue(List<Transaction> transactions){
        System.out.println();
        System.out.print("8. Lowest value of all the transactions:");

        Optional<Transaction> minTransaction=transactions.stream()
            .min(comparing(Transaction::getValue));

        System.out.print(minTransaction.get().getValue());
        System.out.println();
    }
}
