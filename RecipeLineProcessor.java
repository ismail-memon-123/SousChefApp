package cs5279_recipe_parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RecipeLineProcessor {
	private List<String> ingredientDatabase;
	
	public RecipeLineProcessor(String filePath) throws IOException {
		ingredientDatabase = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;
		while ((line = br.readLine()) != null) {
            ingredientDatabase.add(line.trim().toLowerCase());
        }
        br.close();
	}
	
	// these should not clean if inadequate length and not do anything if input is ""
	// just return it back.
    public String cleanPunctuation(String input) {
    	if (input.equalsIgnoreCase("")) {
    		return "";
    	}
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < input.length(); i++) {
    		if ((Character.isLetter(input.charAt(i))) || (Character.isDigit(input.charAt(i)))
    				|| (input.charAt(i) == '/') || (input.charAt(i) == '-')) {
    			sb.append(input.charAt(i));
    		}
    	}
    	return sb.toString();
    }
	
	// UNDERSTAND THIS AND MAYBE cap it to 5 words or somehting bec no need for more than that.
    // Tokenize input and search for each possible ingredient (single or multi-word)
    public void findIngredients(String input) {
        String[] rawWords = input.toLowerCase().split("\\s+");
        String[] words = new String[rawWords.length];
        for (int i = 0; i < words.length; i++) {
        	words[i] = cleanPunctuation(rawWords[i]);
        }
        
        for (int i = 0; i < words.length; i++) {
            for (int j = words.length - 1; j >= i; j--) {
            	StringBuilder phrase = new StringBuilder();
            	for (int k = i; k <= j; k++) {
            		phrase.append(words[k]);
            		if (k != j) {
            			phrase.append(" ");
            		}
            	}

                // Check if the phrase is found in the ingredients list
                int index = Collections.binarySearch(this.ingredientDatabase, phrase.toString());
                if (Collections.binarySearch(this.ingredientDatabase, phrase.toString()) >= 0) {
                    System.out.print("Ingredient: " + phrase.toString() + " at index: " + index + ", ");
                    // (j - i) is difference its 0 if 1 word find if multi then j > i so add that difference to i
                    i += (j - i);
                }
            }
        }
    }
    
	public List<String> getIngredientDatabase() {
		return ingredientDatabase;
	}
	// no need for setter for database.


    public double analyzeWordForTime(String word_input) {
        // if at end there is some [ ] ( ) , . anything it should be stripped
        // same with start
        // if somehting like 30-40 we only want first part. Actually we want second part,
        // so if one-quarter or three-quarters we can focus on that.
        // do those together.
        String word_temp;
        double prefix = 0.0;
        boolean addPrefix = false;
        if (word_input.contains("-")) {
        	String word_prefix = word_input.substring(0, word_input.indexOf('-'));
        	// only a few prefixes here idea is to allow things like twenty-five
        	if (word_prefix.equalsIgnoreCase("twenty")) {
        		prefix = 20.0;
        		addPrefix = true;
        	} else if (word_prefix.equalsIgnoreCase("thirty")) {
        		prefix = 30.0;
        		addPrefix = true;
        	} else if (word_prefix.equalsIgnoreCase("fourty")) {
        		prefix = 40.0;
        		addPrefix = true;
        	} else if (word_prefix.equalsIgnoreCase("fifty")) {
        		prefix = 50.0;
        		addPrefix = true;
        	} else if (word_prefix.equalsIgnoreCase("sixty")) {
        		prefix = 60.0;
        		addPrefix = true;
        	} else if (word_prefix.equalsIgnoreCase("seventy")) {
        		prefix = 70.0;
        		addPrefix = true;
        	} else if (word_prefix.equalsIgnoreCase("eighty")) {
        		prefix = 80.0;
        		addPrefix = true;
        	} else if (word_prefix.equalsIgnoreCase("ninety")) {
        		prefix = 90.0;
        		addPrefix = true;
        	}
            word_temp = word_input.substring(word_input.indexOf('-') + 1);
        } else {
        	word_temp = word_input;
        }
        // dont do with / since that means fractions.
        // i dont think we need to worry about moer than 3 punctuations
        // marks enveloping the number.
        String word = cleanPunctuation(word_temp);
        if (word.equalsIgnoreCase("")) {
        	return 0.0;
        }
        double timeLen = 0.0;
        if (word.equalsIgnoreCase("one")) {
            timeLen = 1.0;
        } else if (word.equalsIgnoreCase("two")) {
            timeLen = 2.0;
        } else if (word.equalsIgnoreCase("three")) {
            timeLen = 3.0;
        } else if (word.equalsIgnoreCase("four")) {
            timeLen = 4.0;
        } else if (word.equalsIgnoreCase("five")) {
            timeLen = 5.0;
        } else if (word.equalsIgnoreCase("six")) {
            timeLen = 6.0;
        } else if (word.equalsIgnoreCase("seven")) {
            timeLen = 7.0;
        } else if (word.equalsIgnoreCase("eight")) {
            timeLen = 8.0;
        } else if (word.equalsIgnoreCase("nine")) {
            timeLen = 9.0;
        } else if (word.equalsIgnoreCase("ten")) {
            timeLen = 10.0;
        } else if (word.equalsIgnoreCase("eleven")) {
            timeLen = 11.0;
        } else if (word.equalsIgnoreCase("twelve")) {
	        timeLen = 12.0;
	    } else if (word.equalsIgnoreCase("thirteen")) {
		    timeLen = 13.0;
		} else if (word.equalsIgnoreCase("fourteen")) {
		    timeLen = 14.0;
		} else if (word.equalsIgnoreCase("fifteen")) {
            timeLen = 15.0;
        }else if (word.equalsIgnoreCase("sixteen")) {
            timeLen = 16.0;
        } else if (word.equalsIgnoreCase("seventeen")) {
            timeLen = 17.0;
        } else if (word.equalsIgnoreCase("eighteen")) {
            timeLen = 18.0;
        } else if (word.equalsIgnoreCase("nineteen")) {
            timeLen = 19.0;
        } else if (word.equalsIgnoreCase("twenty")) {
            timeLen = 20.0;
        } else if (word.equalsIgnoreCase("fourty")) {
            timeLen = 40.0;
        } else if (word.equalsIgnoreCase("fifty")) {
            timeLen = 50.0;
        } else if (word.equalsIgnoreCase("seventy")) {
            timeLen = 70.0;
        } else if (word.equalsIgnoreCase("eighty")) {
            timeLen = 80.0;
        } else if (word.equalsIgnoreCase("ninety")) {
            timeLen = 90.0;
        } else if (word.equalsIgnoreCase("hundred")) {
            timeLen = 100.0;
        } else if (word.equalsIgnoreCase("thirty")) {
            timeLen = 30.0;
        } else if (word.equalsIgnoreCase("sixty")) {
            timeLen = 60.0;
        } else if (word.equalsIgnoreCase("third")) {
            timeLen = 0.33;
        } else if (word.equalsIgnoreCase("thirds")) {
            timeLen = 0.67;
        // having eighths or fifths is too much, can avoid. but keep quarters.   
        } else if (word.equalsIgnoreCase("quarter")) {
            timeLen = 0.25;
        // we dont want half to be far away, half an hour, half minute. Maybe even half of an hour
        } else if (word.equalsIgnoreCase("quarters")) {
            timeLen = 0.75;
        } else if (word.equalsIgnoreCase("half")) {
            timeLen = 0.5;
        // already deal with this case later and this messes up if we have half an hour or something so remove it.
        //} else if (word.equalsIgnoreCase("an")) {
        //    timeLen = 1.0;
        // now we need to deal with actual numbers in the recipe.
        // first lets do fractions there arent more than 3 char fractions x / x.
        } else if (word.length() == 3 && word.charAt(1) == '/' && Character.isDigit(word.charAt(0)) &&
                    Character.isDigit(word.charAt(2))) {
            timeLen = ((double) Character.getNumericValue(word.charAt(0))) / ((double) Character.getNumericValue(word.charAt(2)));
        } else {
            // try parsing as a number.
            try {
                timeLen = Double.parseDouble(word);
            } catch (NumberFormatException nfe) {
                timeLen = 0.0;
            }
        }
        // this does not account for if we have something like twenty- or twenty-garbage but i think
        // that is quite unlikely.
        if (addPrefix) {
        	timeLen += prefix;
        }
        return timeLen;
    }
    // Method to process each line of the paragraph
    public void processLine(String line) {
        // Example task: print the line and count the number of words
        String[] words = line.split("\\s+");  // Split by whitespace
        // Now we need to filter out ANYTHING that does not have either a number, unit of time, or the ingredient
        //ArrayList<String> usefulWords = new ArrayList<String>();
        int startIndex = 0;
        // it would make sense that between the time unit 3 less and 3 more should be seen, with preference given 
        // to before and closer the better.
        for (int i = 0; i < words.length; i++) {
        	//System.out.println(words[i]);
            double timeLen = 0.0;
            String word = words[i];                                                                    
            if (word.equalsIgnoreCase("hrs") || word.equalsIgnoreCase("min") || word.equalsIgnoreCase("sec") || 
            		// there could be the word second as related to 2, so seconds is necessary
            		// not so much as problem with minute or hour. actually maybe keep it and 
            		// ignore it later not have any action for those i cant find numbers for.
            		// actually no keep it bec if it says 1 second no timer needed.
            		word.toLowerCase().contains("seconds") || word.toLowerCase().contains("minute")
            		|| word.toLowerCase().contains("hour") || word.equalsIgnoreCase("hrs.") ||
            		word.equalsIgnoreCase("hr.") || word.equalsIgnoreCase("min.") ||
            		word.equalsIgnoreCase("sec.") || word.equalsIgnoreCase("secs") ||
            		word.equalsIgnoreCase("secs.") ) {
            	// find the time the number should not be more than 3 away from the identifier or time unit.
                for (int j = (i-1); j > Math.max(startIndex, i-4); j--) {
                    timeLen = analyzeWordForTime(words[j]);
                    if (timeLen > 0.05) {
                        // first time we get the time we should stop.
                        break;
                    }
                }
                if (timeLen < 0.05) {
                    // not found time repeat with the forward times. 
                    for (int j = i+1; j < Math.min((i+5), (words.length)); j++) {
                    	timeLen = analyzeWordForTime(words[j]);
                        if (timeLen > 0.05) {
                            // first time we get the time we should stop.
                            break;
                        }
                    }
                }
                // there are a few special cases to check. An hour, a minute, a few <hrs,min,secs> just make it 3
                // unless already set. i think that it being set is a rare case anyway.
                // also these are only done if value not there yet bec what if its half a minute
                // the half would be set no need to make it 1 bec of the a
                if (timeLen < 0.05) {
	                if (((i - 2) >= 0) && words[i-2].equalsIgnoreCase("a") && words[i-1].equalsIgnoreCase("few")) {
	                    if (timeLen < 0.05) {
	                    	timeLen = 3.0;
	                    }
	                }
	                if (((i - 1) >= 0) && (words[i-1].equalsIgnoreCase("a") || words[i-1].equalsIgnoreCase("an") ||
	                		words[i-1].equalsIgnoreCase("another") || words[i-1].equalsIgnoreCase("additional"))) {
	                    timeLen = 1.0;
	                }
                }
                if (timeLen < 0.05) {
                    // if time len is still 0 then we print for now maybe later set to 1??
                	// TODO might be best to not do anything here since it could be some other word.
                    System.out.print(" Unable to parse time setting auto to");
                    timeLen = 1.0;

                }

                // creates identifer based on time and what is necessary in the time label of unit
                // min for minute, hr for hour, else its second. TODO; see maybe you want to do else if
                char identifier;
                if (word.indexOf('m') != -1 && (word.indexOf('i') != -1) && (word.indexOf('n') != -1)) {
                	identifier = 'm';
                } else if (word.indexOf('h') != -1 && (word.indexOf('r') != -1)) {
                	identifier = 'h';
                } else if (word.indexOf('s') != -1 && (word.indexOf('e') != -1) && (word.indexOf('c') != -1)) {
                	identifier = 's';
                } else {
                	identifier = 'i';  // for invalid
                }
                System.out.print(" " + timeLen + " " + identifier);

                startIndex = i+1;
                // After this is done we want to make sure we can look for new times since there can be
                // multiple, TODO we should not ahve to deal with if one of the times is after the unit it should
                // not interfere with the next time. I think a 3 buffer is good to prevent that but maybe testing
                // reveals diff (idk).
            }
        }
    }

    public void processFullRecipe(String fullRecipe) {
        String[] lines = fullRecipe.split("\n");
        System.out.println("Starting process.");
        // Process each line
        for (int i = 0; i < lines.length; i++) {
            System.out.print("\nStep " + (i+1) + ":");
            processLine(lines[i]);
            System.out.print("\n");
            findIngredients(lines[i]);
        }
        System.out.println("\nProcessing finished.");
    }
}