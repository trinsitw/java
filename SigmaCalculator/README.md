# SigmaCalculator

A Java implementation of a recursive identity for computing the sum of k-th powers of the first n positive integers:

$$S(k, n) = \sum_{i=1}^{n} i^k$$

The program derives an exact closed-form polynomial in $n$ for any positive integer $k$, using a recursive identity discovered independently as an alternative to Faulhaber's formula — requiring no reference to Bernoulli numbers.

---

## The Identity

The computation is based on the following theorem (proven in the accompanying paper):

$$\sum_{i=1}^{n} i^k = \left(\sum_{i=1}^{n}(2i-1)\right)\left(\sum_{i=1}^{n} i^{k-2}\right) - \sum_{i=1}^{n}\left((2i-1)\sum_{j=1}^{i}(j-1)^{k-2}\right)$$

valid for integers $k \geq 2$ and positive integers $n \geq 1$.

The key insight is that $\sum_{i=1}^{n}(2i-1) = n^2$ (the sum of the first $n$ odd numbers equals $n^2$), which connects successive even-power sums through a clean recursive structure. Each result is memoized, so computing $S(k, n)$ reuses all previously computed polynomials $S(k-2, n), S(k-4, n), \ldots$ at zero additional cost.

---

## Features

- Derives closed-form polynomial expressions for $S(k, n)$ for arbitrary $k$
- Exact rational arithmetic — no floating-point approximation at any stage
- Memoized recursion: once $S(k, n)$ is computed, it is cached and reused instantly
- Exports results as LaTeX markup
- Verified against classical Faulhaber results for $k = 1$ through $k = 100$

---

## Sample Output

Running `Main` produces LaTeX-formatted closed-form polynomials. A representative excerpt:

```
S(1, n) = \frac{1}{2}n^2 + \frac{1}{2}n
S(2, n) = \frac{1}{3}n^3 + \frac{1}{2}n^2 + \frac{1}{6}n
S(3, n) = \frac{1}{4}n^4 + \frac{1}{2}n^3 + \frac{1}{4}n^2
S(4, n) = \frac{1}{5}n^5 + \frac{1}{2}n^4 + \frac{1}{3}n^3 - \frac{1}{30}n
S(5, n) = \frac{1}{6}n^6 + \frac{1}{2}n^5 + \frac{5}{12}n^4 - \frac{1}{12}n^2
```

All results match the classical Faulhaber formula exactly.

---

## Architecture

The implementation consists of six classes:

| Class | Description |
|---|---|
| `RationalNumber` | Exact rational arithmetic backed by `BigInteger`; immutable, auto-reduced to lowest terms |
| `IndeterminateExponent` | A variable–exponent pair (e.g. $n^3$), used as a building block for monomials |
| `MultivariateMonomial` | A rational-coefficient monomial in multiple indeterminates (e.g. $\tfrac{3}{2}n^2 i$) |
| `MultivariatePolynomial` | Multivariate polynomial algebra with addition, multiplication, and `compose()` for variable substitution |
| `AlternativeToFaulhaberFormula` | Core algorithm: derives $S(k, n)$ via memoized recursion using the identity above |
| `Main` | Entry point; prints LaTeX output for $k = 1$ through $k = 100$ |

### Core Algorithm (simplified)

```java
// For each k, the algorithm:
// 1. Retrieves S(k-2, n) from cache
// 2. Composes S(k-2, n) with (i-1) to obtain S(k-2, i-1) as a polynomial in i
// 3. Multiplies by the odd-number factor (2i-1)
// 4. Sums the resulting polynomial over i = 1..n (recursing into cache)
// 5. Isolates S(k, n) on the left-hand side and stores in cache

MultivariatePolynomial Sk2 = sumOfKthPowersOfFirstNPositiveIntegers(k - 2);
MultivariatePolynomial Sk2AtIMinus1 = Sk2.compose('n', iMinus1);
// ... build and sum (2i-1) * [S(k-2, n) - S(k-2, i-1)]
// ... solve for S(k, n) and cache
```

The polynomial classes are extracted and available as a standalone library: see [ExactPoly](../ExactPoly).

---

## Build and Run

**Requirements:** Java 8+, Maven

```bash
# Clone the repository
git clone https://github.com/trinsitw/java.git
cd java/SigmaCalculator

# Build
mvn compile

# Run (prints LaTeX output for k=1 to k=100)
mvn exec:java -Dexec.mainClass="com.trinwrite.Main"

# Run tests
mvn test
```

---

## Tests

JUnit 4 test suites cover all core classes:

- `RationalNumberTest` — arithmetic operations, reduction, edge cases
- `MultivariateMonomialTest` — multiplication, canonical ordering
- `MultivariatePolynomialTest` — addition, composition, evaluation
- `RationalPolynomialTest` — division algorithm, Horner evaluation
- `AlternativeToFaulhaberFormulaTest` — end-to-end verification against known results

---

## Comparison with Faulhaber's Formula

| Aspect | Faulhaber's Formula | This Identity |
|---|---|---|
| Prerequisites | Bernoulli numbers | Elementary algebra only |
| Form | Direct closed form | Recursive |
| Repeated queries | Recompute each time | O(1) cache lookup |
| Structural insight | Hidden in Bernoulli numbers | Explicit odd-number decomposition |
| Exact rational output | Yes | Yes |

---

## Accompanying Paper

> 📄 **A Recursive Identity for Power Sums: An Alternative to Faulhaber's Formula**
> Trinsit Wasinudomrod, Independent Researcher, 2026
>
> **Download:** [link to paper]

---

## Author

**Trinsit Wasinudomrod**
[github.com/trinsitw](https://github.com/trinsitw) · trinsit.w@gmail.com

---

## License

MIT License. See [LICENSE](../LICENSE).
