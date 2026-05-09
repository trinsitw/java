# Java Mathematical Computing Programs

A collection of Java programs exploring computational mathematics, graph theory, and combinatorial algorithms.

---

## Projects

### SigmaCalculator

An implementation of a recursive formula for computing the sum of k-th powers of the first n positive integers:

$$\sum_{i=1}^{n} i^k$$

This program derives closed-form polynomial expressions in **exact rational arithmetic**, avoiding floating-point approximation errors. It models multivariate polynomials symbolically and uses memoization to compute formulas for arbitrarily large k efficiently.

**Key classes:**
- `RationalNumber` — exact rational arithmetic supporting addition, subtraction, multiplication, division, and power operations
- `RationalPolynomial` — univariate polynomial algebra over rational coefficients, supporting polynomial division and evaluation
- `IndeterminateExponent` — represents a single variable raised to an integer exponent, used as a building block for monomials
- `MultivariateMonomial` — a rational-coefficient monomial in multiple indeterminates (e.g., `(1/2)·n²·i`)
- `MultivariatePolynomial` — multivariate polynomial algebra supporting addition, multiplication, subtraction, and variable substitution via `compose()`
- `AlternativeToFaulhaberFormula` — the core algorithm; derives the closed-form polynomial for Σiᵏ using memoized recursion, with entry point `sumOfKthPowersOfFirstNPositiveIntegers(int k)`
- `Main` — a scratch entry point for manual exploration

**Build:** Maven (`pom.xml`, group `com.trinwrite`, Java 8)

**Tests:** JUnit 4 test suites for `RationalNumber`, `RationalPolynomial`, `MultivariateMonomial`, `MultivariatePolynomial`, and `AlternativeToFaulhaberFormula`

This implementation accompanies a mathematical paper describing and proving the underlying recurrence formula.

> 📄 **Accompanying Paper:** *A Recursive Identity for Power Sums: An Alternative to Faulhaber's Formula*
> 
> **Download:** https://github.com/trinsitw/java/blob/c232120048465a724279b5c746b3e86b75b06230/SigmaCalculator/src/main/java/com/trinwrite/AlternativeToFaulhaberFormula.pdf

---

### ExactPoly

A standalone Java library for exact symbolic arithmetic on univariate and multivariate polynomials with rational coefficients. Originally developed as part of SigmaCalculator, the polynomial classes were extracted and packaged as a reusable, dependency-free library for use in any symbolic computation project.

All polynomial coefficients are stored as exact rational numbers, ensuring no rounding error is introduced across recursive computations. The library's key capability is **polynomial composition** — substituting a variable with another polynomial and returning an exact symbolic result — which enables telescoping verification, recursive substitution, and change-of-variable transformations.

**Key classes:**
- `RationalNumber` — exact rational arithmetic backed by `BigInteger`; immutable, auto-reduced to lowest terms
- `IndeterminateExponent` — a variable–exponent pair (e.g. `n²`), used as a building block for monomials
- `MultivariateMonomial` — a rational-coefficient monomial in multiple indeterminates (e.g. `(3/2)·x²y³`); supports multiplication and canonical ordering
- `MultivariatePolynomial` — multivariate polynomial algebra supporting addition, subtraction, multiplication, `pow`, numerical evaluation, and `compose()` for polynomial-into-polynomial substitution
- `RationalPolynomial` — dense univariate polynomial supporting all arithmetic operations, evaluation via Horner's method, and exact polynomial long division

**Build:** Maven (`pom.xml`, group `com.trinsitw`, artifact `exactpoly`, Java 8)

**Tests:** JUnit 4 test suites for `RationalNumber`, `RationalPolynomial`, `MultivariateMonomial`, and `MultivariatePolynomial`

This library accompanies a paper describing its architecture, design properties, and example applications.

> 📄 **Accompanying Paper:** *ExactPoly: A Java Library for Exact Symbolic Polynomial Arithmetic over the Rationals*
>
> **Download:** https://github.com/trinsitw/java/blob/24e923a2ca65fce82f54aea458b8c6cf15cbc3c1/ExactPoly/src/main/java/com/trinsitw/exactpoly/ExactPoly.pdf

---

### GraphTheory

Graph theory algorithms and data structures implemented in Java, organized into two sub-packages: core algorithms and a forex arbitrage application.

**Core data structures** (`com.intellectworld.graphtheory`):
- `Vertex` — a named graph vertex
- `WeightedDirectedEdge` — a directed edge with a `BigDecimal` weight
- `WeightedDirectedGraph` — a weighted directed graph holding sets of vertices and edges

**Algorithms** (`com.intellectworld.graphtheory.algorithm`):
- `ShortestPathAlgorithm` *(interface)* — contract for single-source shortest path
- `BellmanFordAlgorithm` — implements Bellman-Ford; detects negative cycles and throws `IllegalArgumentException` if one is found
- `CycleDetectionAlgorithm` *(interface)* — contract for cycle enumeration
- `DPTAlgorithm` — depth-first path traversal for enumerating all directed cycles reachable from a given vertex
- `Example` — demonstrates shortest path (Bellman-Ford) and cycle detection (DPT) on sample graphs

**Forex arbitrage application** (`com.intellectworld.graphtheory.forex`):
- `ForexPair` — represents a currency pair and its exchange rate
- `ForexCycle` — a sequence of `ForexPair` objects forming a cycle; computes the compound rate as the product of individual rates
- `ForexNetwork` — models a currency network as a weighted directed graph (edge weights = −log(rate)) and uses `DPTAlgorithm` to find profitable arbitrage cycles above a minimum cycle rate threshold
- `Example` — demonstrates arbitrage detection across USD, EUR, GBP, CHF, and CAD

**Build:** Maven (`pom.xml`, group `com.intellectworld`, Java 8)

**Tests:** JUnit 4 test suites for `AlgorithmTest` and `DPTAlgorithmTest`

---

### Sudoku (Graph Coloring)

A Sudoku modeler and visualizer that represents the puzzle as a graph, where cells are nodes and edges connect cells that cannot share the same value (same row, column, or 3×3 block).

**Classes** (default package, `sudoku/graph/`):
- `SudokuNode` — represents a single cell by `(rowIndex, columnIndex)`; implements `connected(SudokuNode)` which returns `true` if two cells share a row, column, or block
- `SudokuGraph` — constructs the full 81-node graph
- `SudokuPanel` — Swing `JPanel` that renders the graph visually
- `SudokuFrame` — Swing `JFrame` entry point; renders the graph, displays it, and exports a `sudoku.gif` image
- `SudokuMain` — standalone entry point that enumerates and counts all edges in the Sudoku graph

**Note:** This project has no Maven build file; it is a plain Java project intended to be compiled and run directly.

---

## Mathematical Background

The core identity used in SigmaCalculator is:

$$\sum_{i=1}^{n} i^k = \left(\sum_{i=1}^{n}(2i-1)\right)\left(\sum_{i=1}^{n} i^{k-2}\right) - \sum_{i=1}^{n}\left((2i-1)\sum_{j=1}^{i}(j-1)^{k-2}\right)$$

valid for positive integers $k \geq 2$ and $n \geq 1$. This recurrence allows the closed-form polynomial in $n$ for any power $k$ to be derived from lower-order cases, as an alternative to Faulhaber's formula. The derivation requires evaluating $S(k-2, i-1)$ — the polynomial for the lower-order sum with $n$ replaced by $i-1$ — which is performed via polynomial composition using ExactPoly's `compose()` method.

The forex arbitrage detection in GraphTheory is based on the classical reduction: a profitable currency cycle exists if and only if a negative-weight cycle exists in a directed graph where each edge weight is $-\log(\text{exchange rate})$.

---

## Requirements

- Java 8 or higher
- Maven (for SigmaCalculator and GraphTheory)

---

## Author

**Trinsit Wasinudomrod**
[trinsitw](https://github.com/trinsitw) · trinsit.w@gmail.com
