# ExactPoly

A Java library for exact symbolic arithmetic on univariate and multivariate polynomials with rational coefficients.

No floating-point approximation is introduced at any stage. All computations are symbolically correct and numerically exact.

> 📄 **Accompanying paper:** *ExactPoly: A Java Library for Exact Symbolic Polynomial Arithmetic over the Rationals* — [link to paper]
>
> 📄 **Related paper:** *A Recursive Identity for Power Sums: An Alternative to Faulhaber's Formula* — [link to paper]

---

## Features

- Arbitrary-precision rational arithmetic (`RationalNumber`)
- Multivariate monomials and polynomials with rational coefficients
- Polynomial composition (variable substitution)
- Univariate polynomial long division
- Numerical evaluation
- Common-denominator form for LaTeX output
- No external dependencies (Java 8+, standard library only)

---

## Core Classes

| Class | Description |
|---|---|
| `RationalNumber` | Exact rational arithmetic backed by `BigInteger` |
| `IndeterminateExponent` | A variable–exponent pair, e.g. $x^3$ |
| `MultivariateMonomial` | A rational coefficient times a product of variables |
| `MultivariatePolynomial` | A sum of multivariate monomials; supports composition |
| `RationalPolynomial` | Dense univariate polynomial with division |

---

## Quick Start

### Add to your Maven project

```xml
<dependency>
  <groupId>com.trinsitw</groupId>
  <artifactId>exactpoly</artifactId>
  <version>1.0.0</version>
</dependency>
```

Or clone and build locally:

```bash
git clone https://github.com/trinsitw/java.git
cd java/ExactPoly
mvn install
```

### Example: Build and evaluate a polynomial

```java
import com.trinsitw.exactpoly.*;
import java.math.BigInteger;

// S(2,n) = (1/3)n^3 + (1/2)n^2 + (1/6)n
MultivariatePolynomial S2 = new MultivariatePolynomial(
    new MultivariateMonomial(new RationalNumber(1, 3), 'n', 3),
    new MultivariateMonomial(new RationalNumber(1, 2), 'n', 2),
    new MultivariateMonomial(new RationalNumber(1, 6), 'n', 1)
);

// S(2,4) = 1 + 4 + 9 + 16 = 30
System.out.println(S2.evaluate(BigInteger.valueOf(4))); // 30
```

### Example: Polynomial composition

```java
// Substitute n -> (n+1) in S(1,n) = (1/2)n^2 + (1/2)n
MultivariatePolynomial p = new MultivariatePolynomial(
    new MultivariateMonomial(new RationalNumber(1, 2), 'n', 2),
    new MultivariateMonomial(new RationalNumber(1, 2), 'n', 1)
);

MultivariatePolynomial nPlus1 = new MultivariatePolynomial(
    new MultivariateMonomial(new RationalNumber(1), 'n', 1),
    new MultivariateMonomial(new RationalNumber(1))
);

// p(n+1) - p(n) should equal n
MultivariatePolynomial diff = p.compose('n', nPlus1).subtract(p);
System.out.println(diff); // (1)(n^1)
```

### Example: Polynomial long division

```java
// (x^3 - x) / (x - 1) = x^2 + x remainder 0
RationalPolynomial num = new RationalPolynomial(
    RationalNumber.ZERO, new RationalNumber(-1),
    RationalNumber.ZERO, RationalNumber.ONE
);
RationalPolynomial den = new RationalPolynomial(
    new RationalNumber(-1), RationalNumber.ONE
);

RationalPolynomial[] result = num.divide(den);
System.out.println(result[0]); // x^2+x
System.out.println(result[1]); // 0
```

---

## Build

```bash
mvn compile        # compile
mvn test           # run tests
mvn package        # build jar -> target/exactpoly-1.0.0.jar
mvn javadoc:jar    # generate Javadoc
```

---

## Author

**Trinsit Wasinudomrod**
[github.com/trinsitw](https://github.com/trinsitw) · trinsit.w@gmail.com

---

## License

MIT License. See [LICENSE](LICENSE).
