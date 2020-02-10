"""
This file contains functions that are helpful for spelling out numbers.
"""

def digitName(digit):
    """
    Returns a string containing the English word naming "digit".
    "digit" must be between 1 and 9, inclusive
    """
    if digit == 1: return "one"
    if digit == 2: return "two"
    if digit == 3: return "three"
    if digit == 4: return "four"
    if digit == 5: return "five"
    if digit == 6: return "six"
    if digit == 7: return "seven"
    if digit == 8: return "eight"
    if digit == 9: return "nine"
    return ""

def teenName(number):
    """
    Returns a string containing the English word naming "number".
    "number" must be between 10 and nineteen inclusive.
    """
    if number == 10: return "ten"
    if number == 11: return "eleven"
    if number == 12: return "twelve"
    if number == 13: return "thirteen"
    if number == 14: return "fourteen"
    if number == 15: return "fifteen"
    if number == 16: return "sixteen"
    if number == 17: return "seventeen"
    if number == 18: return "eighteen"
    if number == 19: return "nineteen"
    return ""

def tensName(number):
    """
    Returns a string containing the English word for just the tens part of "number".
    "number" must be an integer between 20 and 99 inclusive.
    """
    if number >= 90: return "ninety"
    if number >= 80: return "eighty"
    if number >= 70: return "seventy"
    if number >= 60: return "sixty"
    if number >= 50: return "fifty"
    if number >= 40: return "forty"
    if number >= 30: return "thirty"
    if number >= 20: return "twenty"
    return ""

def spellNumberLessThanThousand(number):
    """
    Returns a string containing the English words that spell out "number".
    "number" must be between 0 and 1000, exclusive.
    """
    part = number    # the part that still needs to be converted
    name = ""        # the name of the number
    if part >= 100:
        name = digitName(part //100) + " hundred"
        part = part % 100
    if part >= 20:
        name = name + " " + tensName(part)
        part = part % 10
    elif part >= 10 :
        name = name + " " + teenName (part)
        part = 0
    if part > 0:
        name = name + " " + digitName (part)
    return name
    
# The function returns the spelling of an integer
# x: the input integer
def spell(x):
    if x == 0:
        return 'zero'
    name = ""
    if (x < 0):
        name = "negative "
        x = -x
    if (x >= 1e6):
        name += spellNumberLessThanThousand(x // 1e6) + " million "
        x %= 1e6
    if (x >= 1e3):
        name += spellNumberLessThanThousand(x // 1e3) + " thousand "
        x %= 1e3
    if (x >= 0):
        name += spellNumberLessThanThousand(x)
    return ' '.join(name.split())

print(spell(123456789)) 
print(spell(456678))  
print(spell(66))      
print(spell(-123456789))
print(spell(-456678) )     
print(spell(-418))     
print(spell(0))      # should print "zero" without the quotes
print(spell(10004))
