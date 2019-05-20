**Contributor:** Zaynab Ghazi
**How to compile:**
**How to run:**
**Known Bugs and limitations:**
**Discussion:**

-- In order to decide uniqueness, I have chosen criteria that I believe satisfy the following:
* very precise criterion that can validate identity
* not very precise but cannot change in 1 year since the input files expected record data throughout a year
* not precise are all but can serve as an extra criterion when there are coincidental similarities
Thus, the criteria is:

* sex
* race
* date of birth
* height in Feet ( since it is less precise than inches which can also change throughout a year)
* eye color
* build ( very general but can serve as an extra identifier when coincidental similarities occur)


Hence, similarity relies on a comparison of a long string containing all the criteria above.



-- Hashing statistics
* Using a sample file of 100 lines and choosing the size of both Hash-maps as SIZE = 120:
I get as an example, the following output:

-For Linear Probing:
Average number of probes:  1.3838383838383839
Max number of probes: 7
Load-factor: 0.6583333333333333

-For Double Hashing:
Average number of probes:  1.2323232323232323
Max number of probes: 4
Load-factor: 0.6583333333333333

Thus, the load-factor is the same and the average n umber of probes is close but not identical. However the maximum number of probes in lear probing is greater than that of double-hashing, which is the intended result since double-hashing reduces the odds of collision.

-- Data for Chart in complexity.png
	M1      M2      M3      M4      M5
8000	3299	24	14	77	22
16000	3232	30	7	91	21
24000	3300	25	13	98	15
32000	3274	27	15	105	20

Such that :
M1 : All Pairs
M2 : Linear Probing Hash Map
M3 : Double Hash Map
M4 : Implemented quick-sort
M5 : Java's built-in quick sort

* We notice that the Double Hash Map deduplication was the fastest and thus most efficient deduplication method.

* We also notice that java's built-in sort is more efficient that my implementation because as I believed it probably relied on more algorithms than just the quick-sort. After some research, it appears that java's sort actually uses more than 6 algorithms and not only quick-sort. It's in fact a Dual-Pivot Quicksort by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch, and it offers O(n log(n)) performance on many data sets that cause other  quicksorts to degrade to quadratic performance.

* After comparing the statistics of the two hash-tables, it seems that the one that uses double-hashing is actually significantly faster and more efficient ( for same size ).That's mainly because double-hashing creates minimum collision compared to linear probing and thus avoids clustering that can be time-expensive. 
-- IMPORTANT NOTE:
- The first line of the file is not counted in the Record Given output.

