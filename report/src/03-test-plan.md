# Test Plan

*   The program has three main screens:
    1.  A list of all animals, displaying key information about each animal.
    2.  A list of all pens, displaying key information about each pen.
    3.  A list of staff, displaying key information about each staff member.

*   The user must be able to add new animals
    *   Add new animal
*   Entering all the information regarding animal requirements.
    *   Add new species

*   The user must be able to add new pens, entering all the information about
the pen.
    *   Add new pens

*   The user must be able to assign animals to pens.
    *   Assign animal to a pen.

*   The user must be able to assign staff to pens.
    *   Assign a staff member to a pen.

*   If a pen is full or otherwise unable to accommodate the animal, the user
should see an error message explaining why.
     *  The UI only displays pens that can accomodate the animal.

*   If a staff member is assigned to an unsuitable pen, the user should see an error message explaining why.
    *   Decided not to implement this requirement.

*   Any animals that have not been assigned a pen should be indicated clearly.
    *   Navigate to animal list with unassigned animals.

*   Any pens that have not had staff assigned should generate an alert of some
kind.
    *   Navigate to pen list with unassigned animals.

*   The program must display the current weather, using data queried from
<https://openweathermap.org/api>
    *   Highlight weather area.

*   The user must be able to refresh the weather data. This should not block the UI whilst the request is made.
    *   Refresh weather data, showing notification.

*   Automatic mode, which automatically tries to allocate animals to the
available pens without input from the user/Automatic mode, which automatically
tries to allocate staff to the available pens without input from the user.
    *   Create animals and pens, run auto-allocator.
