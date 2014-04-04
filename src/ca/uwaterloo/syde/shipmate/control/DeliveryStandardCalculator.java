package ca.uwaterloo.syde.shipmate.control;

import java.util.Date;
import java.util.HashSet;

public class DeliveryStandardCalculator {
	protected String deliveryStandard;
	public String getDeliveryStandard() {
		return deliveryStandard;
	}
	
    /*
     * @param daysToAdd - number of business days to add to selectedInputDate
     * @returns selectedInputDate plus daysToAdd counted by business days
     */
    /*protected Date addBusinessDays(int daysToAdd)
    {
        HashSet<Date> holidays = new HashSet<Date>();

        DefaultHolidayCalendar<Date> holidayCalendar =
            new DefaultHolidayCalendar<Date>(holidays);

        DateKitCalculatorsFactory.getDefaultInstance()
                .registerHolidays("example", holidayCalendar);
        dateCalculator = DateKitCalculatorsFactory.getDefaultInstance()
                .getDateCalculator("example", HolidayHandlerType.FORWARD);
        dateCalculator.setStartDate(selectedInputDate);
        return (dateCalculator).moveByBusinessDays(daysToAdd).getCurrentBusinessDate();
    }*/
}
