Cockpit
===========================================================
## work in progress
This project contains custom RecyclerView's LayoutManager, SnapHelper and ItemDecoration, which used
together can be used, to display grid based app drawer with ViewPager-like experience.

![Swipes](recordings/Cockpit swipes.webm) 
![Last page](recordings/Cockpit last page.webm)
![Drag and drop](recordings/Cockpit drag and drop.webm)
![Vertical](recordings/Cockpit vertical.webm)

### Major components
* CockpitLayoutManager - displays list items in grid with configurable number or rows and columns.
* CockpitSnapHelper - provides ViewPager-like paging functionality.
* CockpitOffsetItemDecoration - fulfills empty space on last page of the list.