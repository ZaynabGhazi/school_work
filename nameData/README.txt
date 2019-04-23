—————————————————
Contributor : Zaynab Ghazi

How to compile:

How to run it : java Main   -f <female name1>  … -f <female nameN>   -m <male name1> … -m <male nameN>  <list of input csv files> 

Knowns Bugs and Limitations: 
—————————————————


Write-up/Description:


	▪	The Name class has 2 instance variable : (String)name and (ArrayList<Data>) yearlyData:
	▪	yearlyData is an ArrayList of Data type:
	▪	Data has 3 (int) instance variables : year, rank and number.
	▪	The yearly statistics of every name are in the names’s arrayList of Data type :
Linked List ==) Name ==) ArrayList<Data> yearlyData ==) Data element ==) number (instance variable of Data object)

	▪	The overall total number of babies of a name that is element (i) of the Doubly  Linked list is stored in element (i-1) in an ArrayList of type Data named totalData<Gender> , while the yearly total of all names of each file is stored in an ArrayList for each gender and is used to compute yearly percentages. 

The total number of babies from all files/years of each gender is stored in an integer variable.
	



	▪	 The Linked Lists are kept in alphabetically sorted order by calling method insertSorted(<Name>) every time a new Name object is created ( insertSorted() is in NameDLL.java)
	▪	The total rank is computed by comparing total numbers: we count the number of total numbers that are less or equal than the total number of the name to be ranked, then we subtract that number from the size of the LinkedList (which equals the number of all names) and add 1.




