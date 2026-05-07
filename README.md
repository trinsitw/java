# Java Mathematical Computing Programs

A collection of Java programs exploring computational mathematics, graph theory, and combinatorial algorithms.

---

## Projects

### SigmaCalculator

An implementation of a recursive formula for computing the sum of k-th powers of the first n positive integers:

$$\sum_{i=1}^{n} i^k$$

This program derives closed-form polynomial expressions in **exact rational arithmetic**, avoiding floating-point approximation errors. It models multivariate polynomials symbolically and uses memoization to compute formulas for arbitrarily large k efficiently.

**Key features:**
- Exact rational number arithmetic via `RationalNumber`
- Symbolic multivariate polynomial algebra (`MultivariatePolynomial`, `MultivariateMonomial`)
- Recursive formula derivation with memoization
- Output in common-denominator form

This implementation accompanies a mathematical paper describing and proving the underlying recurrence formula.

> 📄 **Accompanying Paper:** *A Recursive Identity for Power Sums: An Alternative to Faulhaber's Formula*
> Download: https://github.com/trinsitw/java/blob/c232120048465a724279b5c746b3e86b75b06230/SigmaCalculator/src/main/java/com/trinwrite/AlternativeToFaulhaberFormula.pdf

---

### GraphTheory

Graph theory algorithms and data structures implemented in Java.

---

### Sudoku (Graph Coloring)

A Sudoku solver based on graph coloring techniques, modeling the puzzle as a constraint satisfaction problem on a graph.

---

## Mathematical Background

The core identity used in SigmaCalculator is:

$$\sum_{i=1}^{n} i^k = \left(\sum_{i=1}^{n}(2i-1)\right)\left(\sum_{i=1}^{n} i^{k-2}\right) - \sum_{i=1}^{n}\left((2i-1)\sum_{j=1}^{i}(j-1)^{k-2}\right)$$

valid for positive integers $k \geq 2$ and $n \geq 1$. This recurrence allows the closed-form polynomial in $n$ for any power $k$ to be derived from lower-order cases, as an alternative to Faulhaber's formula.

---

## Requirements

- Java 8 or higher
- Maven (for SigmaCalculator)

---

## Author

**Trinsit Wasinudomrod**
[trinsitw](https://github.com/trinsitw) · trinsit.w@gmail.com
