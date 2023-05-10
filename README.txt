When the program starts, a UserList Class Instance is loaded.
This UserList Class stores the created Users into a TreeMap<String, User>
this TreeMap uses the unique user username as a Key and the User object as a Value.
I chose this data structure for Users because the String username variable is unique, 
which is ideal to use as key. The TreeMap also makes searching for a User by using 
the key efficient with a O(log n).

After Logging in, a PostList Class Instance is loaded. 
This PostList Class stores the Posts created by all Users to a LinkedList<Post>.
This is a API Double Ended LinkedList, I chose this data structure for Posts because it 
keeps the order that the posts were written in and can display the posts with O(n) efficiency. 

Post Class contains a LinkedList<Post> to store the replies to the Post. 
User Class contains a LinkedList<Post> to store the Posts created by that User.
User Class contains a LinkedList<User> to store the Users that the active User follows.
I chose LinkedList for these 3 applications because they would be used primarily 
to display the whole list so for this purpose it was efficient to have O(n). 

The WordSet Class contains a HashSet<String> to store all the words contained in the 
dictionary file that is used for spellCheck. It is wise to use this data structure for two major 
reasons, first is because the dictionary contains unique words and a set will prevent the 
insertion of duplicates, second is because to check the HashSet<String> to see if a word is 
stored has O(1) this is the ideal and most efficient possible data structure. 