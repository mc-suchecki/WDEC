function risk = compute_risk(data)
%The function computes combined risk of price risk, quality risk and
%adveristement risk. It uses parameters returned by risk_approx method
%[Input]:
%   - data - consists of: 1. price 2. quality 3. internet adv 4. magazine
%           adv 5. TV adv
%[Output]:
%   - risk - combined risk
    risk = (hardcoded_compute_risk_from_price(data(1))...
        + hardcoded_compute_risk_from_quality(data(2)) ...
        - 2.248855863597583e-01*compute_risk_from_ad(data(3),data(4),data(5)));       
end

function riskAdv = compute_risk_from_ad(internet,magazine,tv)
%The function computes risk associated with the given internet,magazine and
%tv advertisement. 
%[Input]:
%   - internet - the amount of money spent on internet advertisement
%   - magazine - the amount of money spent on magazine advertisement
%   - tv - the amount of money spent on tv advertisement
%[Output]:
%   - risk - risk for the given advertisement spending
    ad = 0.7*internet + 0.15*magazine + 0.15*tv;
    riskAdv=(0.1)*ad./(internet+magazine+tv+1);
end

function riskPrice = hardcoded_compute_risk_from_price(price)
%The function computes risk associated with the price .
%It uses parameters returned by risk_price_function method.
%[Input]:
%   - price - price at with the product is sold
%[Output]:
%   - risk - risk associated with the price
        riskPrice = -1.876407994407164e-04 *price^4 ...
				+ 1.880084320375905e-02 * price^3 ...
				- 7.016479357822222e-01 * price^2 ...
				+ 1.171126591966163e+01 * price ...
				- 7.422698784891051e+01;
end

function riskQual = hardcoded_compute_risk_from_quality(qual)
%The function computes risk associated with the quality.
%It uses parameters returned by risk_quality_function method.
%[Input]:
%   - quality - quality of the sold product
%[Output]:
%   - risk - risk associated with the the sold product
    riskQual = -2.447239309116428e-08 * qual^4 ...
				+ 4.198060031392230e-06 * qual^3 ...
				- 1.036843178337515e-04 * qual^2 ...
				- 1.910181463955393e-02 * qual ...
				+ 9.988404565796161e-01;
end

