package com.tedit.engine.events;

/*
 * Each event has its own event id, it can be looked at as an GID only local or scoped to the eventhandler
 * it is not autoincremented, but specially formated
 * This is implemented using an int, the id(int) is made up the type and a unique typeid
 * 
 * An example of a id might be 
 *     15187
 *     
 * The int is build up of the following digits
 * - 10^3(thousand) digit and 10^4(ten-thousand) digit) is the type id (possible values 10-99)
 * - 1(first) digit to 10^2(hundred) digit is the unique event id, this number is only unique to the extent of the type as (possible values 0-999)
 *  meaning type 1 and type 2 can have the same eventid as the type id is not the same
 *
 *  TODO: write more about this 
 */
public class EventIdUtil
{
    //Mod with this number to get unique
    //Devide to get type
    public static final int cIdToSeperate = 1000;
    /**
     * Generates a new id for a event with the given typeId
     * @param type of event to generate id for
     * @return returns the unique id on int form
     */
    public static int generateId(int type)
    {
        //TODO: implement
        return 0;
    }
    /**
     * Generates the int form id if both type and and a unique id for the eventtype is known
     * @param type type id
     * @param uniqueId unique number for this type of eventtype
     * @return
     */
    public static int generateId(int type, int uniqueId)
    {
        int eventtype = type*cIdToSeperate;
        return uniqueId+eventtype;
        
    }
}
