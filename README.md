Cockpit
===========================================================
## work in progress
This project contains custom RecyclerView's LayoutManager, SnapHelper and ItemDecoration, which used
together can be used, to display grid based app drawer with ViewPager-like experience.

[![Swipes](https://github.com/dtokarzewski/Cockpit/blob/master/recordings/Cockpit%20swipes.webm)] 
[![Last page](https://github.com/dtokarzewski/Cockpit/blob/master/recordings/Cockpit%20last%20page.webm)]
[![Drag and drop](https://github.com/dtokarzewski/Cockpit/blob/master/recordings/Cockpit%20drag%20and%20drop.webm)]
[![Vertical](https://github.com/dtokarzewski/Cockpit/blob/master/recordings/Cockpit%20vertical.webm)]

### Major components
* CockpitLayoutManager - displays list items in grid with configurable number or rows and columns.
* CockpitSnapHelper - provides ViewPager-like paging functionality.
* CockpitOffsetItemDecoration - fulfills empty space on last page of the list.
