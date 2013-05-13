function [c, ceq] = confuneq(x,volume,result)
% The function is used by fmincon method in get_chart_points function.
% Nonlinear inequality constraints c<=0
    c = -x(1)*volume+volume*hardcoded_compute_unit_cost(x(2)) +x(3) + x(4) + x(5) + result;
    ceq = 0; % dump value 0=0
end

function unit_cost = hardcoded_compute_unit_cost(qual)
%%The function computes unit cost of product based on its quality.
%It uses parameters returned by unit_cost_function method.
%The function is used in constrain statement for minimization of the risk
%at a given result.
%[Input]:
%   - quality - quality of the sold product
%[Output]:
%   - unit_cost - risk associated with the the sold product
    unit_cost = 7.769257747128647e-08 * qual^4 ...
				-  1.745527584856443e-06 * qual^3 ...
				-  7.250322992017221e-04 * qual^2 ...
				+ 7.419703787723134e-02 * qual ...
				+ 7.276718866527457e+00 ;
end