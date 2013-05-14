function [risk, result] = compute_risk_and_result(decisions)
%The function computes combined risk and result for given decisions. It uses parameters returned by risk_approx method
%[Input]:
%   - data - consists of: 1. price 2. quality 3. internet adv 4. magazine
%           adv 5. TV adv 6. volume
%[Output]:
%   - risk - combined risk
%   - result - income for given decisions
    risk =  compute_risk(decisions(1:5));
    result = decisions(1)*decisions(6) - decisions(6)*hardcoded_compute_unit_cost(decisions(2)) - decisions(3) - decisions(4) - decisions(5);
end


