import java.io.File;
import java.util.ArrayList;

/**
 * Hello
* This interface will connect all the classes so that it can be easier to make updates and maintenance
* @author Lim Hong Zhi,
 * */

public interface DataFrame{
    /**
     * Return the name of the dataFrame
     * If no name is give, the default will be DataFrame1
     * @return name
     */
    String getName();

    /**
     * Return the name that is given to the dataframe
     * @param name
     * @return name of dataframe
     */
    void setName(String name);

    /**
     * Return the header (attribute) of the dataframe
     * @return head of the dataframe
     */
    DataFrame head();

}
