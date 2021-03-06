import java.util.ArrayList;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }

    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                    lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                System.out.println("Lot number: " + lotNumber +
                    " already has a bid of: " +
                    selectedLot.getHighestBid().getValue());
            }
        }
    }

    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLot(int lotNumber)
    {
        int index = 0;
        boolean lotEncontrado = false;
        Lot lotBuscado = null;
        while (!lotEncontrado && index<lots.size()) {
            if (lots.get(index).getNumber()==lotNumber) {
                lotBuscado = lots.get(index);
                lotEncontrado = true;
            }
            index++;
        }
        return lotBuscado;  
    }

    /**
     * M�todo que muestra por pantalla los detalles de todos los items que est�n subastandose
     */
    public void close()
    {
        for (Lot lotEnLista : lots) {
            System.out.println("C�digo del item: " + lotEnLista.getNumber());
            System.out.println("Descripci�n del item: " + lotEnLista.getDescription());
            if (lotEnLista.getHighestBid()!=null) {
                System.out.println("Puja m�s alta: " + lotEnLista.getHighestBid().getValue() + ", realizada por: " + lotEnLista.getHighestBid().getBidder().getName());
            }
            else {
                System.out.println("A�n no hay pujas en este item");
            }
        }
    }

    /**
     * M�todo que devuelve una colecci�n de todos los items por los que no ha habido ninguna puja hasta el momento
     */
    public ArrayList<Lot> getUnsold()
    {
        ArrayList<Lot> listaSinVender = new ArrayList<Lot>(); 
        for (Lot lotEnLista : lots) {
            if (lotEnLista.getHighestBid()==null) {
                listaSinVender.add(lotEnLista);
            }
        }
        return listaSinVender;
    }
    
     /**
     * M�todo que elimina un elemento de la lista mediante un par�metro que representa 
     * el n�mero identificativo del item, devolviendo el objeto eliminado o null si este no existe.
     */
    public Lot removeLot(int idNum)
    {
        int index = 0;
        boolean lotEncontrado = false;
        Lot lotEliminado = null;
        while (!lotEncontrado && index<lots.size()) {
            if (lots.get(index).getNumber()==idNum) {
                lotEliminado = lots.get(index);
                lots.remove(index);
                lotEncontrado = true;
            }
            index++;
        }
        return lotEliminado;
    }
}
