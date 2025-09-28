import java.util.ArrayList;

public class DNAtoAminoAcid {

    // Function 1: Convert DNA sequence to codons (3-letter chunks)
    public static ArrayList<String> DNAToCodons(String dna) {
        ArrayList<String> codons = new ArrayList<>();
        // Loop through the DNA string in increments of 3
        for (int i = 0; i < dna.length(); i += 3) {
            // Extract each 3-letter codon and add it to the list
            codons.add(dna.substring(i, Math.min(i + 3, dna.length())));
        }
        return codons;
    }

    // Function 2: Convert a codon to its corresponding amino acid
    public static String CodonToAminoAcid(String codon) {
        // Using switch-case to map codons to amino acids
        switch (codon) {
            case "TTT": case "TTC": return "F";  // Phenylalanine
            case "TTA": case "TTG": case "CTT": case "CTC": case "CTA": case "CTG": return "L";  // Leucine
            case "ATT": case "ATC": case "ATA": return "I";  // Isoleucine
            case "ATG": return "M";  // Methionine
            case "GTT": case "GTC": case "GTA": case "GTG": return "V";  // Valine
            case "TCT": case "TCC": case "TCA": case "TCG": return "S";  // Serine
            case "CCT": case "CCC": case "CCA": case "CCG": return "P";  // Proline
            case "ACT": case "ACC": case "ACA": case "ACG": return "T";  // Threonine
            case "GCT": case "GCC": case "GCA": case "GCG": return "A";  // Alanine
            case "TAT": case "TAC": return "Y";  // Tyrosine
            case "TAA": case "TAG": case "TGA": return "Stop";  // Stop codon
            case "CAT": case "CAC": return "H";  // Histidine
            case "CAA": case "CAG": return "Q";  // Glutamine
            case "AAT": case "AAC": return "N";  // Asparagine
            case "AAA": case "AAG": return "K";  // Lysine
            case "GAT": case "GAC": case "GAA": case "GAG": return "D";  // Aspartic Acid
            case "TGT": case "TGC": return "C";  // Cysteine
            case "TGG": return "W";  // Tryptophan
            case "CGT": case "CGC": case "CGA": case "CGG": case "AGA": case "AGG": return "R";  // Arginine
            case "GGT": case "GGC": case "GGA": case "GGG": return "G";  // Glycine
            default: return "Unknown";  // In case of an unrecognized codon
        }
    }

    // Function 3: Convert DNA to amino acid sequence
    public static ArrayList<String> dna_to_amino_acid(String dna) {
        ArrayList<String> codons = DNAToCodons(dna);  // Convert DNA to codons
        ArrayList<String> aminoAcids = new ArrayList<>();
        
        for (String codon : codons) {
            aminoAcids.add(CodonToAminoAcid(codon));  // Convert each codon to an amino acid
        }
        
        return aminoAcids;
    }

    // Function 4: Check if two amino acid sequences match
    public static boolean is_match(ArrayList<String> amino_seq1, ArrayList<String> amino_seq2) {
        return amino_seq1.equals(amino_seq2);  // Return true if the sequences are identical
    }

    public static void main(String[] args) {
        // DNA sequences to compare
        String DNA1 = "CTGATATTGTATCCGGCCGAA";
        String DNA2 = "CTAGCCGGTGGTTATTAATAGTAAACTATTCCA";
        String DNA3 = "TTAATCCTCTACCCCGCAGAG";

        // Convert DNA sequences to amino acid sequences
        ArrayList<String> aminoSeq1 = dna_to_amino_acid(DNA1);
        ArrayList<String> aminoSeq2 = dna_to_amino_acid(DNA2);
        ArrayList<String> aminoSeq3 = dna_to_amino_acid(DNA3);

        // Debug: Print codons and amino acids for all DNA sequences
        System.out.println("DNA1 Codons: " + DNAToCodons(DNA1));
        System.out.println("DNA1 Amino Acids: " + aminoSeq1);
        System.out.println("DNA2 Codons: " + DNAToCodons(DNA2));
        System.out.println("DNA2 Amino Acids: " + aminoSeq2);
        System.out.println("DNA3 Codons: " + DNAToCodons(DNA3));
        System.out.println("DNA3 Amino Acids: " + aminoSeq3);

        // Compare the amino acid sequences and print the results
        System.out.println("DNA1 vs DNA2: " + is_match(aminoSeq1, aminoSeq2));  // Compare DNA1 and DNA2
        System.out.println("DNA1 vs DNA3: " + is_match(aminoSeq1, aminoSeq3));  // Compare DNA1 and DNA3
        System.out.println("DNA2 vs DNA3: " + is_match(aminoSeq2, aminoSeq3));  // Compare DNA2 and DNA3
    }
}

